package net.majora.binarytranslator.mixin;

import net.minecraft.text.LiteralTextContent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LiteralTextContent.class)
abstract class LiteralTextContentMixin {
	@Shadow
	@Final
	@Mutable
	private String string;

	@Inject(method = "<init>", at = @At("TAIL"))
	private void init(String string, CallbackInfo ci) {
		StringBuilder sb = new StringBuilder();
		string.chars().forEach(i -> {
			String val = "00000000" + Integer.toBinaryString(i);
			sb.append(val.substring(val.length() - 8)).append(' ');
		});

		this.string = sb.toString();
	}
}