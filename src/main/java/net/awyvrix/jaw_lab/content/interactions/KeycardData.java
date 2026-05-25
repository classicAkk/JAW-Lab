package net.awyvrix.jaw_lab.content.interactions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public record KeycardData(int level, String owner, String uuid, String network) {

    public KeycardData withChangedLevel(int l) {
        return new KeycardData(level + l, owner, uuid, network);
    }

    public KeycardData withLevel(int level) {
        return new KeycardData(level, owner, uuid, network);
    }

    public KeycardData withOwner(String owner) {
        return new KeycardData(level, owner, uuid, network);
    }

    public KeycardData withUUID(String uuid) {
        return new KeycardData(level, owner, uuid, network);
    }

    public KeycardData withNetwork(String network) {
        return new KeycardData(level, owner, uuid, network);
    }

    public static final Codec<KeycardData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("level").forGetter(KeycardData::level),
                    Codec.STRING.fieldOf("owner").forGetter(KeycardData::owner),
                    Codec.STRING.fieldOf("uuid").forGetter(KeycardData::uuid),
                    Codec.STRING.fieldOf("network").forGetter(KeycardData::network)
            ).apply(instance, KeycardData::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, KeycardData> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT, KeycardData::level,
                    ByteBufCodecs.STRING_UTF8, KeycardData::owner,
                    ByteBufCodecs.STRING_UTF8, KeycardData::uuid,
                    ByteBufCodecs.STRING_UTF8, KeycardData::network,
                    KeycardData::new
            );

    public static KeycardData get(ItemStack stack) {
        KeycardData existing = stack.get(LabDataComponents.KEYCARD.get());

        if (existing != null) return existing;
        CustomData custom = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = custom.copyTag();

        if (tag.isEmpty()) return new KeycardData(0, "none", "none", "none");
        KeycardData migrated = new KeycardData(
                tag.getInt("cLevel"),
                tag.getString("cOwner"),
                tag.getString("cUUID"),
                tag.getString("cNetwork")
        );

        stack.set(LabDataComponents.KEYCARD.get(), migrated);
        CompoundTag newTag = custom.copyTag();
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(newTag));

        return migrated;
    }
}