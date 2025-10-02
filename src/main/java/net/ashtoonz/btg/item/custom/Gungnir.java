package net.ashtoonz.btg.item.custom;

import net.ashtoonz.btg.entity.custom.FallingGungnirEntity;
import net.ashtoonz.btg.entity.custom.MagicCircleEntity;
import net.minecraft.block.AirBlock;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.ExplosionBehavior;

import java.util.List;

public class Gungnir extends SwordItem {
    private PlayerEntity player;
    private int state = 0;

    private final double fallingSpearReach = 30;
    private final double spearSpawnRadius = 50;
    private final double spearSpawnHeight = 30;
    private final float spearFallSpeed = 2f;
    private final int spearAmount = 5;

    public Gungnir(Settings settings) {
        super(ToolMaterials.NETHERITE, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(user.getWorld().isClient())
            return super.use(world, user, hand);
        if(state == 0) {
            EntityHitResult entityHitResult = ProjectileUtil.raycast(
                    user,
                    user.getEyePos(),
                    user.getEyePos().add(user.getRotationVec(1).multiply(fallingSpearReach)),
                    user.getBoundingBox().stretch(user.getRotationVec(1).multiply((fallingSpearReach))).expand(2.0),
                    (entity) -> !entity.isSpectator() && entity.isAlive() && entity.canHit(),
                    fallingSpearReach);

            if(entityHitResult != null) {
                MagicCircleEntity magicCircle = new MagicCircleEntity(user.getWorld(), entityHitResult.getEntity());
                magicCircle.setPosition(entityHitResult.getEntity().getPos().subtract(0, 1, 0));

                user.getWorld().spawnEntity(magicCircle);

                spawnFallingSpear(entityHitResult.getEntity(), user, spearAmount, null, magicCircle);

                return TypedActionResult.success(user.getStackInHand(hand));
            }

            BlockHitResult blockHitResult = (BlockHitResult) user.raycast(fallingSpearReach, 1, true);
            if(!(user.getWorld().getBlockState(blockHitResult.getBlockPos()).getBlock() instanceof AirBlock)) {
                MagicCircleEntity magicCircle = new MagicCircleEntity(user.getWorld());
                magicCircle.setPosition(blockHitResult.getPos());

                user.getWorld().spawnEntity(magicCircle);

                spawnFallingSpear(blockHitResult.getPos(), user, spearAmount, null, magicCircle);

                return TypedActionResult.success(user.getStackInHand(hand));
            }
        }
        return super.use(world, user, hand);
    }

    public boolean spawnFallingSpear(Vec3d targetPos, PlayerEntity user, int index, FallingGungnirEntity explosionParent, MagicCircleEntity magicCircle) {
        FallingGungnirEntity gungnirEntity;
        if(index == spearAmount)
            gungnirEntity = new FallingGungnirEntity(user.getWorld(), user, index == 1, index, this, targetPos, spearFallSpeed, explosionParent, magicCircle);
        else
            gungnirEntity = new FallingGungnirEntity(user.getWorld(), user, index == 1, index, this, targetPos, spearFallSpeed, explosionParent);

        double spawnX = targetPos.getX()+Math.random()*spearSpawnRadius-spearSpawnRadius/2;
        double spawnY = targetPos.getY()+spearSpawnHeight;
        double spawnZ = targetPos.getZ()+Math.random()*spearSpawnRadius-spearSpawnRadius/2;

        if(!(user.getWorld().getBlockState(BlockPos.ofFloored(new Vec3d(spawnX, spawnY, spawnZ))).getBlock() instanceof AirBlock)) {
//            spawnX = targetPos.getX();
//            spawnY = targetPos.getY()+1;
//            spawnZ = targetPos.getZ();
            user.sendMessage(Text.literal("There isn't enough space forOdin's full power..."));
            if(magicCircle != null)
                magicCircle.kill();
            return false;
        } else {
            gungnirEntity.setPos(spawnX, spawnY, spawnZ);

            user.getWorld().spawnEntity(gungnirEntity);
            gungnirEntity.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, targetPos);
            gungnirEntity.setVelocity(gungnirEntity, gungnirEntity.getPitch(), gungnirEntity.getYaw(), 0, spearFallSpeed, 0);
            return true;
        }
    }

    public boolean spawnFallingSpear(Entity targetEntity, PlayerEntity user, int index, FallingGungnirEntity explosionParent, MagicCircleEntity magicCircle) {
        Vec3d targetPos = targetEntity.getPos();
        if(index == 0) {
            return false;
        }

        FallingGungnirEntity gungnirEntity;
        if(index == spearAmount)
            gungnirEntity = new FallingGungnirEntity(user.getWorld(), user, index == 1, index, this, targetEntity, spearFallSpeed, explosionParent, magicCircle);
        else
            gungnirEntity = new FallingGungnirEntity(user.getWorld(), user, index == 1, index, this, targetEntity, spearFallSpeed, explosionParent);

        double spawnX = targetPos.getX()+Math.random()*spearSpawnRadius-spearSpawnRadius/2;
        double spawnY = targetPos.getY()+spearSpawnHeight;
        double spawnZ = targetPos.getZ()+Math.random()*spearSpawnRadius-spearSpawnRadius/2;

        if(!(user.getWorld().getBlockState(BlockPos.ofFloored(new Vec3d(spawnX, spawnY, spawnZ))).getBlock() instanceof AirBlock)) {
//            spawnX = targetPos.getX();
//            spawnY = targetPos.getY()+1;
//            spawnZ = targetPos.getZ();
            user.sendMessage(Text.literal("Odin's power can't reach here..."));
            if(magicCircle != null)
                magicCircle.kill();
            return false;
        } else {
            gungnirEntity.setPos(spawnX, spawnY, spawnZ);

            user.getWorld().spawnEntity(gungnirEntity);
            gungnirEntity.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, targetPos);
            gungnirEntity.setVelocity(gungnirEntity, gungnirEntity.getPitch(), gungnirEntity.getYaw(), 0, spearFallSpeed, 0);
            return true;
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if(entity instanceof PlayerEntity) {
            player = (PlayerEntity) entity;
        }
    }

    public void changeState(int amount) {
        state += amount;

        if(state > 2) {
            state = 0;
        }
        if(state < 0) {
            state = 2;
        }

        if(state == 0)
            player.sendMessage(Text.literal("State: Handheld"));
        if(state == 1)
            player.sendMessage(Text.literal("State: Sentry"));
        if(state == 2)
            player.sendMessage(Text.literal("State: Control"));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        tooltip.add(Text.literal("Legendary spear infused with Odin's power"));
        tooltip.add(Text.literal(""));
        if(state == 0) {
            tooltip.add(Text.literal("State: Handheld").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("Right-click to summon the powers of Odin.").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("Homes-in on targeted entities.").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("(Shift + Scroll to change states)").formatted(Formatting.AQUA));
        }
        if(state == 1) {
            tooltip.add(Text.literal("State: Sentry").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("Automatically protects you. Targets").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("the closest hostile mobs first.").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("(Shift + Scroll to change states)").formatted(Formatting.AQUA));
        }
        if(state == 2) {
            tooltip.add(Text.literal("State: Control").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("").formatted(Formatting.AQUA));
            tooltip.add(Text.literal("(Shift + Scroll to change states)").formatted(Formatting.AQUA));
        }
    }
}
