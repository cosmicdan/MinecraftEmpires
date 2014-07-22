package com.cosmicdan.minecraftempires.entities.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class TileEntityCampfire extends TileEntity {
    
    public int metadata;
    public boolean isCooking;
    
    public TileEntityCampfire() {}
    
    public TileEntityCampfire(World world, int metadata) {
        this.setWorldObj(world);
        this.metadata = metadata;
    }
    
    @Override
    public void writeToNBT(NBTTagCompound entityData) {
        super.writeToNBT(entityData);
        entityData.setBoolean("isCooking", isCooking);
    }

    @Override
    public void readFromNBT(NBTTagCompound entityData) {
        super.readFromNBT(entityData);
        this.isCooking = entityData.getBoolean("isCooking");
        if (this.hasWorldObj()) {
            if (metadata > 0) {
                // nothing yet
            }
        }
    }
 }