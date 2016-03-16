package minecraftflightsimulator.planes.MC172;

import minecraftflightsimulator.entities.EntityPlane;
import minecraftflightsimulator.modelrenders.ModelRenderHelper;
import minecraftflightsimulator.modelrenders.RenderPlane;
import minecraftflightsimulator.models.ModelPlane;
import minecraftflightsimulator.other.HUDParent;

import org.lwjgl.opengl.GL11;

public class RenderMC172 extends RenderPlane {

	public RenderMC172(){
		super(new ModelMC172());
	}

	@Override
	protected void renderWindows(){
		this.renderManager.renderEngine.bindTexture(ModelPlane.windowTexture);
    	ModelRenderHelper.startRender();
		ModelRenderHelper.renderQuad(-0.75, -0.75, 0.75, 0.75, 1.625, 0.625, 0.625, 1.625, 0.875, 1.75, 1.75, 0.875, true);
		ModelRenderHelper.renderTriangle(-0.75, -0.75, -0.75, 1.625, 0.625, 0.625, 0.875, 0.875, 1.75, true);
		ModelRenderHelper.renderTriangle(0.75, 0.75, 0.75, 1.625, 0.625, 0.625, 0.875, 0.875, 1.75, true);
		ModelRenderHelper.renderSquare(0.85, 0.85, 0.625, 1.625, -0.25, 0.625, true);
		ModelRenderHelper.renderSquare(-0.85, -0.85, 0.625, 1.625, -0.25, 0.625, true);
		ModelRenderHelper.renderTriangle(-0.85, -0.85, -0.7, 1.6, 0.625, 0.625, -0.5, -0.5, -1.95, true);
		ModelRenderHelper.renderTriangle(0.85, 0.85, 0.7, 1.6, 0.625, 0.625, -0.5, -0.5, -1.95, true);
		ModelRenderHelper.renderQuad(-0.8, -0.525, 0.525, 0.8, 1.625, 0.625, 0.625, 1.625, -0.5, -2.1, -2.1, -0.5, true);
		ModelRenderHelper.endRender();
	}

	@Override
	protected void renderConsole(EntityPlane plane){
		GL11.glPushMatrix();
		GL11.glTranslatef(0.92F, 0.35F, 0.715F);
		GL11.glRotatef(180, 0, 0, 1);
		GL11.glScalef(0.00390625F*1.5F, 0.00390625F*1.5F, 0.00390625F*1.5F);
		for(int i=0; i<plane.instrumentList.size(); ++i){
			if(plane.instrumentList.get(i) != null){
				int type = plane.instrumentList.get(i).getItemDamage();
				if(i==0 || i==5){
					GL11.glPushMatrix();
					GL11.glRotatef(-90, 0, 1, 0);
					GL11.glTranslatef(-80, 0, -30);
					GL11.glScalef(0.75F, 0.75F, 0.75F);
					HUDParent.drawInstrument(plane, 72 + (i%5)*62, i<5 ? -10 : 52, type, false);
					GL11.glPopMatrix();
				}else if(i==4 || i==9){
					GL11.glPushMatrix();
					GL11.glScalef(0.75F, 0.75F, 0.75F);
					HUDParent.drawInstrument(plane, 72 + (i%5)*62, i<5 ? -10 : 52, type, false);
					GL11.glPopMatrix();
				}else{
					HUDParent.drawInstrument(plane, (i%5)*62, i<5 ? 0 : 62, type, false);
				}
			}
		}
		HUDParent.drawInstrument(plane, 272, -5, 15, false);
		HUDParent.drawInstrument(plane, 272, 60, 16, false);
		HUDParent.drawInstrument(plane, 232, 80, 17, false);
		GL11.glPopMatrix();
	}
}
