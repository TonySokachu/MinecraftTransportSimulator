package minecraftflightsimulator.packets.control;

import io.netty.buffer.ByteBuf;
import minecraftflightsimulator.MFS;
import minecraftflightsimulator.entities.EntityPlane;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class ElevatorPacket implements IMessage{
	private int id;
	private byte elevatorAngle;	

	public ElevatorPacket() { }
	
	public ElevatorPacket(int id, byte elevatorAngle){
		this.id=id;
		this.elevatorAngle=elevatorAngle;
	}
	
	@Override
	public void fromBytes(ByteBuf buf){
		this.id=buf.readInt();
		this.elevatorAngle=buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf){
		buf.writeInt(this.id);
		buf.writeByte(this.elevatorAngle);
	}

	public static class ElevatorPacketHandler implements IMessageHandler<ElevatorPacket, IMessage> {
		public IMessage onMessage(ElevatorPacket message, MessageContext ctx) {
			EntityPlane thisEntity;
			if(ctx.side==Side.SERVER){
				thisEntity = (EntityPlane) ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.id);
			}else{
				thisEntity = (EntityPlane) Minecraft.getMinecraft().theWorld.getEntityByID(message.id);
			}
			if(thisEntity!=null){
				if(ctx.side==Side.SERVER){
					if(message.elevatorAngle + thisEntity.elevatorAngle >= -250 && message.elevatorAngle + thisEntity.elevatorAngle <= 250){
						thisEntity.elevatorAngle += message.elevatorAngle;
						thisEntity.elevatorCooldown = MFS.controlSurfaceCooldown;
						MFS.MFSNet.sendToAll(message);
					}
				}else{
					thisEntity.elevatorAngle += message.elevatorAngle;
				}
			}
			return null;
		}
	}

}
