package net.ashtoonz.btg.entity.custom;

import net.ashtoonz.btg.entity.ModEntities;
import net.ashtoonz.btg.item.ModItems;
import net.ashtoonz.btg.item.custom.Gungnir;
import net.minecraft.block.AirBlock;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FallingGungnirEntity extends PersistentProjectileEntity {
    private static final int EXPLODE_DELAY = 10;
    private int explodeTimer = -1;
    private boolean explodes;

    private static final int SPEAR_DELAY = 0;
    private int spearTimer = SPEAR_DELAY;
    private int index;

    private static final int MAX_LIFETIME = 300;
    private int lifetime = 0;

    private PlayerEntity user;
    private Gungnir item;
    private Vec3d targetPos;
    private Entity targetEntity;
    private float speed;

    private boolean hit = false;
    private Entity hitEntity;
    private Vec3d hitOffset;
    private double hitYaw;
    private double hitPitch;

    private FallingGungnirEntity explosionParent;
    private MagicCircleEntity magicCircle;

    public FallingGungnirEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public FallingGungnirEntity(World world, PlayerEntity player, boolean explodes, int index, Gungnir item, Vec3d targetPos, float speed, FallingGungnirEntity explosionParent, MagicCircleEntity magicCircle) {
        super(ModEntities.FALLING_GUNGNIR, player, world, new ItemStack(ModItems.GUNGNIR), null);

        this.index = index;
        this.explodes = explodes;

        user = player;
        this.item = item;
        this.targetPos = targetPos;
        this.speed = speed;

        this.explosionParent = explosionParent;
        this.magicCircle = magicCircle;
    }

    public FallingGungnirEntity(World world, PlayerEntity player, boolean explodes, int index, Gungnir item, Vec3d targetPos, float speed, FallingGungnirEntity explosionParent) {
        super(ModEntities.FALLING_GUNGNIR, player, world, new ItemStack(ModItems.GUNGNIR), null);

        this.index = index;
        this.explodes = explodes;

        user = player;
        this.item = item;
        this.targetPos = targetPos;
        this.speed = speed;

        this.explosionParent = explosionParent;
    }

    public FallingGungnirEntity(World world, PlayerEntity player, boolean explodes, int index, Gungnir item, Entity targetEntity, float speed, FallingGungnirEntity explosionParent, MagicCircleEntity magicCircle) {
        super(ModEntities.FALLING_GUNGNIR, player, world, new ItemStack(ModItems.GUNGNIR), null);

        this.index = index;
        this.explodes = explodes;

        user = player;
        this.item = item;
        this.targetPos = targetEntity.getPos();
        this.targetEntity = targetEntity;
        this.speed = speed;

        this.explosionParent = explosionParent;
        this.magicCircle = magicCircle;
    }

    public FallingGungnirEntity(World world, PlayerEntity player, boolean explodes, int index, Gungnir item, Entity targetEntity, float speed, FallingGungnirEntity explosionParent) {
        super(ModEntities.FALLING_GUNGNIR, player, world, new ItemStack(ModItems.GUNGNIR), null);

        this.index = index;
        this.explodes = explodes;

        user = player;
        this.item = item;
        this.targetPos = targetEntity.getPos();
        this.targetEntity = targetEntity;
        this.speed = speed;

        this.explosionParent = explosionParent;
    }

    @Override
    public void tick() {
        super.tick();

        setNoGravity(true);

        if(lifetime >= MAX_LIFETIME) {
            explode();
        }

        lifetime ++;


        if(explodeTimer == 0) {
            explode();
        }

        explodeTimer --;

        if(spearTimer == 0 && item != null && hit && index > 1) {
            if(targetEntity != null) {
                boolean success = item.spawnFallingSpear(targetEntity, user, index - 1, this, magicCircle);
                if(!success) {
                    explode();
                }
            } else {
                boolean success = item.spawnFallingSpear(targetPos, user, index - 1, this, magicCircle);
                if(!success) {
                    explode();
                }
            }
        }

        if(hit)
            spearTimer --;

        if(hitEntity != null) {
            setPosition(hitEntity.getPos().add(hitOffset));
            setRotation((float) hitYaw, (float) hitPitch);
            setVelocity(Vec3d.ZERO);
        }

        if(targetEntity != null && targetEntity.isAlive()) {
            targetPos = targetEntity.getPos();
        } else if(targetPos != null) {
            while(getWorld().getBlockState(BlockPos.ofFloored(targetPos.subtract(0, 1, 0))).getBlock() instanceof AirBlock && targetPos.getY() > -65) {
                targetPos = Vec3d.of(BlockPos.ofFloored(targetPos)).subtract(0, 1, 0);
                if(magicCircle != null)
                    magicCircle.setPosition(targetPos);
            }
        }

        if(targetPos != null && hitEntity == null) {
            lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, targetPos);
            setVelocity(this, getPitch(), getYaw(), 0, speed, 0);
            lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, targetPos);
        }

        getWorld().addParticle(ParticleTypes.FIREWORK, getX(), getY(), getZ(), 0, 0, 0);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.GUNGNIR);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (explodes)
            explodeTimer = EXPLODE_DELAY;
        this.pickupType = PickupPermission.DISALLOWED;

        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, getWorld());
        lightning.setPosition(getPos());
        lightning.setCosmetic(true);

        getWorld().spawnEntity(lightning);
        hit = true;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (user != null)
            entityHitResult.getEntity().damage(user.getDamageSources().playerAttack(user), 5);
        if (hitEntity == null) {
            if (explodes)
                explodeTimer = EXPLODE_DELAY;
            hitEntity = entityHitResult.getEntity();
            hitOffset = getPos().subtract(hitEntity.getPos()).multiply(0.3).add(0, hitEntity.getBoundingBox().getLengthY()/2, 0);
            hitPitch = getPitch();
            hitYaw = getYaw();

            LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, getWorld());
            lightning.setPosition(getPos());
            lightning.setCosmetic(true);

            getWorld().spawnEntity(lightning);
        }
        hit = true;
    }

    @Override
    public void onExplodedBy(@Nullable Entity entity) {
        //kill();
    }

    public static int getExplodeDelay() {
        return EXPLODE_DELAY;
    }

    public int getExplodeTimer() {
        return explodeTimer;
    }

    public void explode() {
        if(explosionParent != null)
            explosionParent.explode();
        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, getWorld());
        lightning.setPosition(getPos());
        lightning.setCosmetic(true);

        getWorld().createExplosion(this, getPos().x, getPos().y, getPos().z, 2.0f, World.ExplosionSourceType.TNT);
        getWorld().spawnEntity(lightning);

        getWorld().addParticle(ParticleTypes.FIREWORK, getX(), getY(), getZ(), 0, 0, 0);

        if(magicCircle != null)
            magicCircle.kill();
        kill();
    }
}
