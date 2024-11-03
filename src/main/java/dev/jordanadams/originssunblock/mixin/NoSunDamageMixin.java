package dev.jordanadams.originssunblock.mixin;

import dev.jordanadams.originssunblock.OriginsSunBlock;
import io.github.apace100.apoli.power.BurnPower;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import net.minecraft.entity.LivingEntity;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BurnPower.class)
public abstract class NoSunDamageMixin extends Power {
  @Unique
  private static final Logger LOGGER = OriginsSunBlock.Companion.getLOGGER();

  public NoSunDamageMixin(PowerType<?> type, LivingEntity entity) {
    super(type, entity);
  }

  @Inject(at = @At("HEAD"), method = "tick", cancellable = true, remap = false)
  public void tick(CallbackInfo ci) {
    if (OriginsSunBlock.Companion.cancelBurnPower(this.entity.getArmorItems())) {
      ci.cancel();
    }
  }
}


