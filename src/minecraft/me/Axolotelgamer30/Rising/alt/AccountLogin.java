package me.Axolotelgamer30.Rising.alt;

import java.util.UUID;

import org.lwjgl.input.Keyboard;

import com.mojang.authlib.Agent;
import com.mojang.authlib.AuthenticationService;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.util.UUIDTypeAdapter;

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.Session;

public class AccountLogin extends GuiScreen {
	public GuiTextField username;
	public GuiTextField password;
	public String mode = "Microsoft";
	
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	ScaledResolution sr = new ScaledResolution(mc);
    	drawDefaultBackground();
        drawString(mc.fontRendererObj, "To log into a cracked account, just leave the password box blank.", 1, 1, -1);
        drawString(mc.fontRendererObj, "§7Your Username: §f" + mc.getSession().getUsername(), 1, 10, -1);
        drawString(mc.fontRendererObj, "§7Your Service: §f" + (password.getText().isEmpty() ? "Cracked Service" : mode), 1, 19, -1);
        drawString(mc.fontRendererObj, "Username:", width / 2 - 50 - 10, sr.getScaledHeight() / 2 - 60, -1);
        drawString(mc.fontRendererObj, "Password:", width / 2 - 50 - 10, sr.getScaledHeight() / 2 - 26, -1);
        username.drawTextBox();
        password.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    public void initGui() {
    	ScaledResolution sr = new ScaledResolution(mc);
        buttonList.clear();
        buttonList.add(new GuiButton(0, width / 2 - 50 - 10, height / 2 + 9, 120, 20, "Login"));
        buttonList.add(new GuiButton(1, (int) (width / 2F + 50), height - 24, 100, 20, "Exit"));
        buttonList.add(new GuiButton(2, (int) (width / 2F - 50), height - 24, 100, 20, "Microsoft"));
        buttonList.add(new GuiButton(3, (int) (width / 2F - 150), height - 24, 100, 20, "Mojang"));
        (username = new GuiTextField(100, fontRendererObj, width / 2 - 50 - 10, sr.getScaledHeight() / 2 - 50, 120, 20)).setFocused(true);
        (password = new GuiTextField(100, fontRendererObj, width / 2 - 50 - 10, sr.getScaledHeight() / 2 - 16, 120, 20)).setFocused(false);
        Keyboard.enableRepeatEvents(true);
        SessionChanger.setup();
    }
    
    @Override
    protected void actionPerformed(GuiButton button) {
    	switch(button.id) {
	    	case 0:
	    		if(username.getText() != " ")
	            	if(password.getText().isEmpty())
	            		SessionChanger.setUserOffline(username.getText());
	            	else if(mode == "Microsoft")
	                	SessionChanger.setUserMicrosoft(username.getText(), password.getText());
	            	else if(mode == "Mojang")
	                	SessionChanger.setUserMojang(username.getText(), password.getText());
	    		break;
	    	case 1:
	    		 mc.displayGuiScreen(new GuiMainMenu());
	    		 break;
	    	case 2:
	    		mode = "Microsoft";
	    		break;
	    	case 3:
	    		mode = "Mojang";
	    		break;
    	}
    }
    
    @Override
    protected void keyTyped(final char character, final int key) {
        try {
            super.keyTyped(character, key);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (character == '\t' && !username.isFocused()) {
            username.setFocused(true);
            password.setFocused(false);
        }
        if (character == '\t' && !password.isFocused()) {
            password.setFocused(true);
            username.setFocused(false);
        }
        if (character == '\r')
            actionPerformed(buttonList.get(0));
        username.textboxKeyTyped(character, key);
        password.textboxKeyTyped(character, key);
    }
    
    @Override
    protected void mouseClicked(final int x2, final int y2, final int button) {
        try {
            super.mouseClicked(x2, y2, button);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        username.mouseClicked(x2, y2, button);
        password.mouseClicked(x2, y2, button);
    }
    
    @Override
    public void onGuiClosed() {
        mc.entityRenderer.loadEntityShader(null);
        Keyboard.enableRepeatEvents(false);
    }
    
    @Override
    public void updateScreen() {
        username.updateCursorCounter();
        password.updateCursorCounter();
    }
    
    public static class SessionChanger {
    	public static UserAuthentication auth;
    	public static Minecraft mc = Minecraft.getMinecraft();
    	
    	public static void setup() {
    		UUID uuid = UUID.randomUUID();
    		AuthenticationService authService = new YggdrasilAuthenticationService(mc.getProxy(), uuid.toString());
    		auth = authService.createUserAuthentication(Agent.MINECRAFT);
    		authService.createMinecraftSessionService();
    	}
    	
    	public static void setUserOffline(String username) {
    		try {
    			auth.logOut();
    			mc.session = new Session(username, username, "0", "mojang");
    		}
    		catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	public static void setUserMojang(String email, String password) {
    		if(mc.getSession().getUsername() != email) {
    			auth.logOut();
    			auth.setUsername(email);
    			auth.setPassword(password);
    			try {
    				auth.logIn();
    				mc.session = new Session(auth.getSelectedProfile().getName(), UUIDTypeAdapter.fromUUID(auth.getSelectedProfile().getId()), auth.getAuthenticatedToken(), auth.getUserType().getName());;
    			}
    			catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	
    	public static void setUserMicrosoft(String email, String password) {
    		MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
    		try {
    			MicrosoftAuthResult acc = authenticator.loginWithCredentials(email, password);
    			mc.session = new Session(acc.getProfile().getName(), acc.getProfile().getId(), acc.getAccessToken(), "legacy");
    		}
    		catch (MicrosoftAuthenticationException e) {
    			e.printStackTrace();
    		}
    	}
    }
}