package ml.northwestwind.skyfarm.client.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import ml.northwestwind.skyfarm.client.config.SkyFarmConfig;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class ConfigCommand {
    public static void registerCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("skybox")
                .then(Commands.argument("value", BoolArgumentType.bool()).executes(ConfigCommand::enableSkybox)));
        dispatcher.register(Commands.literal("horizon")
                .then(Commands.argument("value", BoolArgumentType.bool()).executes(ConfigCommand::disableHorizon)));
        dispatcher.register(Commands.literal("fog")
                .then(Commands.argument("value", BoolArgumentType.bool()).executes(ConfigCommand::disableFog)));
    }

    private static int enableSkybox(CommandContext<CommandSource> context) {
        Entity entity = context.getSource().getEntity();
        if (entity == null) return 0;
        boolean value = BoolArgumentType.getBool(context, "value");
        SkyFarmConfig.setGogSkybox(value);
        entity.sendMessage(
                new StringTextComponent("")
                        .append(new TranslationTextComponent("config.skyfarm.skybox." + value).setStyle(Style.EMPTY.applyFormat(value ? TextFormatting.GREEN : TextFormatting.RED)))
                        .append(new TranslationTextComponent("config.skyfarm.skybox")),
                Util.NIL_UUID);
        return 1;
    }

    private static int disableHorizon(CommandContext<CommandSource> context) {
        Entity entity = context.getSource().getEntity();
        if (entity == null) return 0;
        boolean value = BoolArgumentType.getBool(context, "value");
        SkyFarmConfig.setNoHorizon(value);
        entity.sendMessage(
                new StringTextComponent("")
                        .append(new TranslationTextComponent("config.skyfarm.horizon." + value).setStyle(Style.EMPTY.applyFormat(value ? TextFormatting.GREEN : TextFormatting.RED)))
                        .append(new TranslationTextComponent("config.skyfarm.horizon")),
                Util.NIL_UUID);
        return 1;
    }

    private static int disableFog(CommandContext<CommandSource> context) {
        Entity entity = context.getSource().getEntity();
        if (entity == null) return 0;
        boolean value = BoolArgumentType.getBool(context, "value");
        SkyFarmConfig.setNoFog(value);
        entity.sendMessage(
                new StringTextComponent("")
                        .append(new TranslationTextComponent("config.skyfarm.fog." + value).setStyle(Style.EMPTY.applyFormat(value ? TextFormatting.GREEN : TextFormatting.RED)))
                        .append(new TranslationTextComponent("config.skyfarm.fog")),
                Util.NIL_UUID);
        return 1;
    }
}
