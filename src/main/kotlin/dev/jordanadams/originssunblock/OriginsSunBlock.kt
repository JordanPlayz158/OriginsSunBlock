package dev.jordanadams.originssunblock

import dev.jordanadams.originssunblock.enchantment.SunBlockEnchantment
import net.fabricmc.api.ModInitializer
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class OriginsSunBlock : ModInitializer {
  companion object {
    private const val MOD_ID = "originssunblock";
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

    val SUN_BLOCK = SunBlockEnchantment()

    var lastCustomDamageSource: Pair<String, DamageSource>? = null

    fun cancelBurnPower(items: Iterable<ItemStack>): Boolean {
      for (item in items) {
        val enchantments = item.enchantments
        for (i in enchantments.indices) {
          val enchantmentCompound = enchantments.getCompound(i)

          val enchantment = Registries.ENCHANTMENT.getOrEmpty(
            EnchantmentHelper.getIdFromNbt(enchantmentCompound))
          if (enchantment.isPresent && enchantment.get() == SUN_BLOCK) {
            return true
          }
        }
      }

      return false
    }
  }


  override fun onInitialize() {
    Registry.register(Registries.ENCHANTMENT, Identifier(MOD_ID, "sun_block"), SUN_BLOCK)
  }
}
