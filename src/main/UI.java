package main;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static java.awt.Font.createFont;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB;
    public boolean messageOn;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = ""; // this is for dialog
    public int commandNum = 0;
    public int titleScreenState = 0; //0: the first screen, 1: the second screen,...
    public int slotCol=0;
    public int slotRow=0;
    public UI(GamePanel gp) {
        this.gp = gp;
        messageOn = false;
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/DroidSans.ttf");
            maruMonica = createFont(Font.TRUETYPE_FONT, is);// coi nhu la phong chu 1
            is = getClass().getResourceAsStream("/fonts/DroidSans-Bold.ttf");
            purisaB = createFont(Font.TRUETYPE_FONT, is);// phong chu 2
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(maruMonica);
//      g2.setFont(purisaB);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        // DIALOGUE STATE (More advanced)
        if(gp.gameState == gp.dialogueState){
            switch (messageCounter){
                case 1: currentDialogue="Hello Knight";break;
                case 2: currentDialogue="How are you";break;
                case 3: currentDialogue="Do you something here?";break;
                case 4: currentDialogue="May I help you?";break;
                case 5:
                    gp.gameState=gp.playState;
                    messageCounter=0;
                    messageOn=false;
                    break;
            }
            drawDialogueScreen();
        }
        //Inventory
        if(gp.gameState==gp.characterState){
            drawCharacterScreen();
            drawInventory();
        }
    }

    public void drawTitleScreen() {

        if (titleScreenState == 0) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Mystery Knight";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 2 ;

            // SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);

            // MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //Image
            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize*2 ;
            g2.drawImage(gp.player.downSprites[0], x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));

            text = "NEW GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize * 5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize ;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "QUIT";
            x = getXForCenteredText(text);
            y += gp.tileSize ;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        } else if (titleScreenState == 1) {
            //CLASS SELECTION SCREEN
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your class!";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXForCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "Thief";
            x = getXForCenteredText(text);
            y += (int) (gp.tileSize*1.5);
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "Sorcerer";
            x = getXForCenteredText(text);
            y += (int) (gp.tileSize*1.5);
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "Back";
            x = getXForCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }

    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "PAUSE";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text,x,y);
    }

    public void drawDialogueScreen() {

        //	WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*3;

        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawCharacterScreen(){
        //Create a Frame
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*6;
        final int frameHeight = gp.tileSize*6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        //Text
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Weapons", textX, textY);

        //Values
        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gp.tileSize;
        String value;

        value = gp.player.life + "/" + gp.player.maxLife;
        textX = getXForAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY+=lineHeight;
        value = "Sword";
        textX = getXForAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);

//        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY, null);
//        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY, null);


    }

    public void drawInventory(){

        //FRAME
        int frameX = gp.tileSize*9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        //DrawPlayerItems
        for(int i = 0; i < gp.player.inventory.size(); i ++) {
            g2.drawImage(gp.player.inventory.get(i).image, slotX, slotY,gp.tileSize,gp.tileSize, null);
            slotX += slotSize;
            if(i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        // CURSOR
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        // DRAW CURSOR
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // DESCRIPTION FRAME
        int dFrameY = frameY + frameHeight;
        int dFrameHeight = gp.tileSize*3;
        drawSubWindow(frameX,dFrameY, frameWidth,dFrameHeight);

        // DRAW DESCRIPTION TEXT
        int textX = frameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        int  itemIndex = getItemIndexOnSlot();
        if (itemIndex < gp.player.inventory.size()) {
            for (String line: gp.player.inventory.get(itemIndex).description.split("\n")) {

                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }

    }

    public int getItemIndexOnSlot() {
        return slotCol + (slotRow*5);
    }
    //some useful method
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }

    public int getXForAlignToRightText(String text, int tailX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - length;
    }


}



