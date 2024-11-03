package dev.jordanadams.originssunblock.mixin;

import dev.jordanadams.originssunblock.OriginsSunBlock;
import io.github.apace100.apoli.data.DamageSourceDescription;
import kotlin.Pair;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DamageSourceDescription.class)
public class HackyMixinToGetProperDamageSourceNameAsNameMutableDamageSourceAdditionsHaveNotBeenBackportedAndStaticInjectDidntSeemToWorkForDamageAction {

  @Shadow @Final private String name;

  @Inject(at = @At("HEAD"), method = "overwriteDamageSourceMessageKey")
  public void overwriteDamageSourceMessageKey(DamageSource source, CallbackInfo ci) {
    OriginsSunBlock.Companion.setLastCustomDamageSource(new Pair<>(name, source));
  }
}
