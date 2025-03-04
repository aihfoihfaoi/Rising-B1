package me.Axolotelgamer30.Rising.alt;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Session;

public class GuiMSAltLogin extends GuiScreen{
	private final GuiAltManager manager = new GuiAltManager();
    private PasswordField password;
    private String status = (Object)((Object)EnumChatFormatting.GRAY) + "Idle...";
    private GuiTextField username;
	
    
    
    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0: {
            	MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
    			try {
    				System.out.print(this.username.getText() + " : " + this.password.getText() + "\n");
    				MicrosoftAuthResult result = authenticator.loginWithCredentials(this.username.getText(), this.password.getText());
    						
    						Session session = new Session(
    								result.getProfile().getName(),
    								result.getProfile().getId(),
    								result.getAccessToken(),
    								"mojang");
    						mc.session = session;
    						System.out.print(mc.getSession().getProfile().getName());
    						
    			} catch (MicrosoftAuthenticationException e) {
    				e.printStackTrace();
    			}
    			this.mc.displayGuiScreen(this.manager);
            }
            case 1: {
                this.mc.displayGuiScreen(this.manager);
            }
        }
    }
    
    @Override
    public void drawScreen(int i2, int j2, float f2) {
        this.drawDefaultBackground();
        this.username.drawTextBox();
        this.password.drawTextBox();
        this.drawCenteredString(this.fontRendererObj, "Login", width / 2, 20, -1);
        if (this.username.getText().isEmpty()) {
            this.drawString(this.mc.fontRendererObj, "E-Mail", width / 2 - 96, 66, -7829368);
        }
        if (this.password.getText().isEmpty()) {
            this.drawString(this.mc.fontRendererObj, "Password", width / 2 - 96, 106, -7829368);
        }
        this.drawCenteredString(this.fontRendererObj, this.status, width / 2, 30, -1);
        super.drawScreen(i2, j2, f2);
    }

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 92 + 12, "Login"));
        this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 116 + 12, "Back"));
        this.username = new GuiTextField(this.eventButton, this.mc.fontRendererObj, width / 2 - 100, 60, 200, 20);
        this.password = new PasswordField(this.mc.fontRendererObj, width / 2 - 100, 100, 200, 20);
    }

    @Override
    protected void keyTyped(char par1, int par2) {
        this.username.textboxKeyTyped(par1, par2);
        this.password.textboxKeyTyped(par1, par2);
        if (par1 == '\t' && (this.username.isFocused() || this.password.isFocused())) {
            this.username.setFocused(!this.username.isFocused());
            this.password.setFocused(!this.password.isFocused());
        }
        if (par1 == '\r') {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3) {
        try {
            super.mouseClicked(par1, par2, par3);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.username.mouseClicked(par1, par2, par3);
        this.password.mouseClicked(par1, par2, par3);
    }

}
