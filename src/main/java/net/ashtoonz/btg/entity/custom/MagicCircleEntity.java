package net.ashtoonz.btg.entity.custom;

import net.ashtoonz.btg.entity.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MagicCircleEntity extends Entity {
    private Entity targetEntity;

    public MagicCircleEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    public MagicCircleEntity(World world) {
        super(ModEntities.MAGIC_CIRCLE, world);
    }

    public MagicCircleEntity(World world, Entity targetEntity) {
        super(ModEntities.MAGIC_CIRCLE, world);

        this.targetEntity = targetEntity;
    }

    @Override
    public void tick() {
        super.tick();

        if(targetEntity != null && targetEntity.isAlive()) {
            setPosition(Vec3d.of(targetEntity.getBlockPos()).add(targetEntity.getBoundingBox().getLengthX()/2, 0, targetEntity.getBoundingBox().getLengthZ()/2));
        }
    }

    @Override
    public void onExplodedBy(@Nullable Entity entity) {
        //kill();
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
