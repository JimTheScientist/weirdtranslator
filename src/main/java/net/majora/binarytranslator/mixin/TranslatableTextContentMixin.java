package net.majora.binarytranslator.mixin;

import net.minecraft.text.TranslatableTextContent;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TranslatableTextContent.class)
abstract class TranslatableTextContentMixin {
    @Shadow
    @Final
    @Mutable // i don't think i need this but I'm too lazy to run the game again and check
    private String key;
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(String string, CallbackInfo ci) {
        StringBuilder sb = new StringBuilder();
        string.chars().forEach(i -> {
            String val = "00000000" + Integer.toBinaryString(i);
            sb.append(val.substring(val.length() - 8)).append(' ');
        });
        this.key = sb.toString();
    }
}
