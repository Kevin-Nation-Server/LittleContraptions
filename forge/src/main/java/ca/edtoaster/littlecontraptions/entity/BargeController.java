package ca.edtoaster.littlecontraptions.entity;
import com.simibubi.create.content.contraptions.minecart.capability.MinecartController;
import dev.murad.shipping.capability.StallingCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.UUID;

/**
 * Compat layer with Create's Minecart Controller
 */
public class BargeController extends MinecartController {

    public static BargeController EMPTY;
    private final WeakReference<ContraptionBargeEntity> weakRef;
    private final LazyOptional<StallingCapability> stallingCapability;

    public BargeController(ContraptionBargeEntity entity) {
        super(null);
        weakRef = new WeakReference<>(entity);
        stallingCapability = entity == null ? LazyOptional.empty() : entity.getCapability(StallingCapability.STALLING_CAPABILITY);
    }

    public void tick() {
    }

    public boolean isFullyCoupled() {
        return false;
    }

    public boolean isLeadingCoupling() {
        return false;
    }

    public boolean isConnectedToCoupling() {
        return false;
    }

    public boolean isCoupledThroughContraption() {
        return false;
    }

    public boolean hasContraptionCoupling(boolean current) {
        return false;
    }

    public float getCouplingLength(boolean leading) {
        return 0;
    }

    public void decouple() {
    }

    public void removeConnection(boolean main) {
    }

    public void prepareForCoupling(boolean isLeading) {
    }

    public void coupleWith(boolean isLeading, UUID coupled, float length, boolean contraption) {
    }

    @Nullable
    public UUID getCoupledCart(boolean asMain) {
        return null;
    }

    public boolean isStalled() {
        return stallingCapability.map(StallingCapability::isFrozen).orElse(false);
    }

    public void setStalledExternally(boolean stall) {
        stallingCapability.ifPresent(cap -> {
            if(stall) {
                cap.freeze();
            } else {
                cap.unfreeze();
            }
        });
    }

    public void sendData() {
    }

    @Override
    public CompoundTag serializeNBT() {
        return new CompoundTag();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
    }

    public boolean isPresent() {
        return weakRef.get() != null && barge().isAlive();
    }

    public ContraptionBargeEntity barge() {
        return weakRef.get();
    }

    public static BargeController empty() {
        return EMPTY != null ? EMPTY : (EMPTY = new BargeController(null));
    }
}
