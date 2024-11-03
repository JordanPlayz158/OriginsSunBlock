package dev.jordanadams.originssunblock.mixin;

import dev.jordanadams.originssunblock.OriginsSunBlock;
import io.github.apace100.apoli.access.NameMutableDamageSource;
import kotlin.Pair;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class NoSunDamageVampireCompatMixin {

  @Shadow public abstract Iterable<ItemStack> getArmorItems();

  @Unique
  private static final Logger LOGGER = OriginsSunBlock.Companion.getLOGGER();

  @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
  private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
    // Yes this is awful, yes I hope that there is a better way, if there is one, let me know
    //   Which would probably mean you also found a fix for HackyMixinToGetProperDamage...
    Pair<String, DamageSource> pair = OriginsSunBlock.Companion.getLastCustomDamageSource();

    if (pair == null || pair.component2() != source) {
      return;
    }

    String realSourceName = pair.component1();
    LOGGER.info("Proper Source Name: {}", realSourceName);

    if (realSourceName.equals("sun_damage")
        && OriginsSunBlock.Companion.cancelBurnPower(this.getArmorItems())) {
      cir.setReturnValue(false);
    }
  }
}
