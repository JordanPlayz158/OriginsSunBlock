package dev.jordanadams.originssunblock.mixin;

import dev.jordanadams.originssunblock.OriginsSunBlock;
import io.github.apace100.apoli.power.factory.condition.EntityConditions;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityConditions.class)
public class NoSunDamageMixin {
  @Inject(at = @At("HEAD"), method = "lambda$register$9", cancellable = true)
  private static void sunDamagePrevention(SerializableData.Instance data,
      Entity player,
      CallbackInfoReturnable<Boolean> cir) {
    if (OriginsSunBlock.Companion.hasHelmetWithSunBlockEnchantment(player.getArmorItems())) {
      cir.setReturnValue(false);
    }
  }
}


