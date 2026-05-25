package net.awyvrix.jaw_lab.content.networking;

import net.awyvrix.jaw_lab.content.networking.handler.*;
import net.awyvrix.jaw_lab.content.networking.packet.doors.*;
import net.awyvrix.jaw_lab.content.networking.packet.keycards.*;
import net.awyvrix.jaw_lab.content.networking.packet.menu.OpenCopyMenuPacket;
import net.awyvrix.jaw_lab.content.networking.packet.menu.OpenMainMenuPacket;
import net.awyvrix.jaw_lab.content.networking.packet.menu.OpenNetworkMenuPacket;
import net.awyvrix.jaw_lab.content.networking.packet.network.*;
import net.awyvrix.jaw_lab.Lab;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Lab.MOD_ID)
public final class LabNetworking {
    private LabNetworking() {}

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1.0");

        // Door
        registrar.playToServer(
                OpenDoorPacket.TYPE,
                OpenDoorPacket.STREAM_CODEC,
                DoorPacketHandler::handleOpenDoor
        );

        registrar.playToServer(
                SetCodePacket.TYPE,
                SetCodePacket.STREAM_CODEC,
                DoorPacketHandler::handleSetCode
        );

        registrar.playToServer(
                ResetDoorPacket.TYPE,
                ResetDoorPacket.STREAM_CODEC,
                DoorPacketHandler::handleResetDoor
        );

        registrar.playToServer(
                SwitchAutoClosePacket.TYPE,
                SwitchAutoClosePacket.STREAM_CODEC,
                DoorPacketHandler::handleSwitchAutoClose
        );

        registrar.playToServer(
                SetDoorNetworkPacket.TYPE,
                SetDoorNetworkPacket.STREAM_CODEC,
                DoorPacketHandler::handleSetDoorNetwork
        );

        registrar.playToServer(
                IncrementDoorLevelPacket.TYPE,
                IncrementDoorLevelPacket.STREAM_CODEC,
                DoorPacketHandler::handleIncrementDoorLevel
        );

        registrar.playToServer(
                DecrementDoorLevelPacket.TYPE,
                DecrementDoorLevelPacket.STREAM_CODEC,
                DoorPacketHandler::handleDecrementDoorLevel
        );

        // Keycard
        registrar.playToServer(
                ResetKeycardPacket.TYPE,
                ResetKeycardPacket.STREAM_CODEC,
                KeycardPacketHandler::handleResetKeycard
        );

        registrar.playToServer(
                SetUUIDPacket.TYPE,
                SetUUIDPacket.STREAM_CODEC,
                KeycardPacketHandler::handleSetUUID
        );

        registrar.playToServer(
                SetUsernamePacket.TYPE,
                SetUsernamePacket.STREAM_CODEC,
                KeycardPacketHandler::handleSetUsername
        );

        registrar.playToServer(
                IncreaseCardLevelPacket.TYPE,
                IncreaseCardLevelPacket.STREAM_CODEC,
                KeycardPacketHandler::handleIncreaseCardLevel
        );

        registrar.playToServer(
                DecreaseCardLevelPacket.TYPE,
                DecreaseCardLevelPacket.STREAM_CODEC,
                KeycardPacketHandler::handleDecreaseCardLevel
        );

        registrar.playToServer(
                ResetCardLevelPacket.TYPE,
                ResetCardLevelPacket.STREAM_CODEC,
                KeycardPacketHandler::handleResetCardLevel
        );

        registrar.playToServer(
                AddCardNetworkPacket.TYPE,
                AddCardNetworkPacket.STREAM_CODEC,
                KeycardPacketHandler::handleAddCardNetwork
        );

        registrar.playToServer(
                RemoveCardNetworkPacket.TYPE,
                RemoveCardNetworkPacket.STREAM_CODEC,
                KeycardPacketHandler::handleRemoveCardNetwork
        );

        registrar.playToServer(
                CopyCardPacket.TYPE,
                CopyCardPacket.STREAM_CODEC,
                KeycardPacketHandler::handleCopyCard
        );

        // network
        registrar.playToServer(
                CreateNetworkPacket.TYPE,
                CreateNetworkPacket.STREAM_CODEC,
                NetworkPacketHandler::handleCreateNetwork
        );

        registrar.playToServer(
                DeleteNetworkPacket.TYPE,
                DeleteNetworkPacket.STREAM_CODEC,
                NetworkPacketHandler::handleDeleteNetwork
        );

        registrar.playToServer(
                FindUserPacket.TYPE,
                FindUserPacket.STREAM_CODEC,
                NetworkPacketHandler::handleFindUser
        );

        registrar.playToServer(
                AddUserPacket.TYPE,
                AddUserPacket.STREAM_CODEC,
                NetworkPacketHandler::handleAddUser
        );

        registrar.playToServer(
                RemoveUserPacket.TYPE,
                RemoveUserPacket.STREAM_CODEC,
                NetworkPacketHandler::handleRemoveUser
        );

        registrar.playToServer(
                SetUserRolePacket.TYPE,
                SetUserRolePacket.STREAM_CODEC,
                NetworkPacketHandler::handleSetUserRole
        );

        registrar.playToServer(
                IncreaseUserLevelPacket.TYPE,
                IncreaseUserLevelPacket.STREAM_CODEC,
                NetworkPacketHandler::handleIncreaseUserLevel
        );

        registrar.playToServer(
                DecreaseUserLevelPacket.TYPE,
                DecreaseUserLevelPacket.STREAM_CODEC,
                NetworkPacketHandler::handleDecreaseUserLevel
        );

        // Menu
        registrar.playToServer(
                OpenNetworkMenuPacket.TYPE,
                OpenNetworkMenuPacket.STREAM_CODEC,
                MenuPacketHandler::handleOpenNetworkMenu
        );

        registrar.playToServer(
                OpenMainMenuPacket.TYPE,
                OpenMainMenuPacket.STREAM_CODEC,
                MenuPacketHandler::handleOpenMainMenu
        );

        registrar.playToServer(
                OpenCopyMenuPacket.TYPE,
                OpenCopyMenuPacket.STREAM_CODEC,
                MenuPacketHandler::handleOpenCopyMenu
        );

        // Code Door
        registrar.playToServer(
                CodeDoorSubmitPacket.TYPE,
                CodeDoorSubmitPacket.STREAM_CODEC,
                CodeDoorPacketHandler::handleSubmit
        );
    }
}