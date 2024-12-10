package main;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static java.awt.Font.createFont;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB;
    public boolean messageOn;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messCount= new ArrayList<>();
    int messageCounter = 0;
    int scenarioState=0;
    public boolean gameFinished = false;
    public String currentDialogue = ""; // this is for dialog
    public int commandNum = 0;
    public int titleScreenState = 0; //0: the first screen, 1: the second screen,...
    public int slotCol=0;
    public int slotRow=0;
    private HashMap<String,Integer> countItems;
    public UI(GamePanel gp) {
        this.gp = gp;
        messageOn = false;
        setDefault();
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
    //scrolling message;
    public void setDefault(){
        countItems=new HashMap<>();
        countItems.put("Slime Heart",0);
        countItems.put("Stone",0);
        countItems.put("Legend Sword",0);
        countItems.put("Dragon Shield",0);
        countItems.put("Mushroom",0);
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
            setContent();
            drawDialogueScreen();
        }
        //Inventory
        if(gp.gameState==gp.characterState){
            drawCharacterScreen();
            drawInventory();
        }
        drawMessage();

    }
    public void drawMessage(){
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,28F));
        int i=0;
        for (String text : message ){
            g2.setColor(Color.black);
            g2.drawString(text,messageX+2,messageY+2);

            g2.setColor(Color.white);
            g2.drawString(text,messageX,messageY);
            messCount.set(i,messCount.get(i)+1);
            messageY+= 50;
            i++;
        }
        while (!message.isEmpty() && messCount.getFirst()>180){
            message.removeFirst();
            messCount.removeFirst();
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

            text = "TUTORIAL";
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

            String text = "Do you want to skip the tutorial?";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Yes";
            x = getXForCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "No";
            x = getXForCenteredText(text);
            y += (int) (gp.tileSize*1.5);
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
        if (titleScreenState==2){
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Press A,W,S,D to move";
            int x = getXForCenteredText(text)-gp.tileSize*3;
            int y = gp.tileSize * 2;
            g2.drawString(text, x, y);

            text = "Press P to Pause";
            y+=gp.tileSize;
            g2.drawString(text,x,y);

            text = "Press L to attack";
            y+=gp.tileSize;
            g2.drawString(text,x,y);

            text = "Press K to shoot";
            y+=gp.tileSize;
            g2.drawString(text,x,y);

            text = "Press C to open inventory";
            y+=gp.tileSize;
            g2.drawString(text,x,y);

            text = "Press Enter to gather, communicate";
            y+=gp.tileSize;
            g2.drawString(text,x,y);

            text = "Play";
            x=getXForAlignToRightText(text,15*gp.tileSize);
            y+=gp.tileSize*4;
            g2.drawString(text,x,y);
            g2.drawString(">", x - gp.tileSize, y);

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

    public void setContent(){
        if (scenarioState==0){
            switch (messageCounter){
                case 1: currentDialogue="Old village:\nGood morning Young Knight!";break;
                case 2: currentDialogue="Knight:\nYeah,Good morning!";break;
                case 3: currentDialogue="Knight:\nWhat is happening?\nWhere are everybody?";break;
                case 4: currentDialogue="Old village:\nSome of they have luckily escaped, others \nare killed by that monster.....";break;
                case 5: currentDialogue="Knight:\nHuh...";break;
                case 6: currentDialogue="Knight:\nThey are killed.....";break;
                case 7: currentDialogue="Knight:\nWhere....where..is..that monster???";break;
                case 8: currentDialogue="Knight:\nGru....h.h!";break;
                case 9: currentDialogue="Old village:\nYour power now is not sufficient.You may\n be killed";break;
                case 10:currentDialogue="Old village:\nHold on!";break;
                case 11:currentDialogue="Knight:\nWhy are you still here?. Why don't you \nfollow to other people?";break;
                case 12:currentDialogue="Old village:\nI am a wizard.\nI know you will come here!";break;
                case 13:currentDialogue="Old village:\nYou are strong but not enough..";break;
                case 14:currentDialogue="Old village:\nDon't worry..I will teach you a special\n skill!";break;
                case 15:currentDialogue="Old village:\nYou move to South and you will see slimes\n and shits";break;
                case 16:currentDialogue="Old village:\nKill them and give me the reward!";break;
                case 17:currentDialogue="                     Mission unlock!\nGather 3 slime hear and 2 stone";break;
                case 18:messageCounter=0;scenarioState=1;gp.gameState= gp.playState;break;
            }
        }
        else if (scenarioState==1){
            currentDialogue="                   Mission Procession\n"+countItems.get("Slime Heart")+"/3 slime heart\n"+countItems.get("Stone")+"/2 stone";
            if (messageCounter==2 && countItems.get("Slime Heart")>=3 && countItems.get("Stone")>=2) {
                gp.gameState = gp.playState;
                countItems.put("Slime Heart",countItems.get("Slime Heart")-3);
                countItems.put("Stone",countItems.get("Stone")-2);
                scenarioState=2;
                messageCounter = 0;
            }
            else if (messageCounter==2){
                gp.gameState=gp.playState;
                messageCounter=0;
            }
        }
        else if (scenarioState==2){
            switch (messageCounter){
                case 1:currentDialogue="Old village:\nGood job!";break;
                case 2:currentDialogue="Old village:\nIt requires a little bit time!";break;
                case 3:currentDialogue="Old village:\nYeah,Complete!";break;
                case 4:currentDialogue="Old village:\nCome here!";
                case 5:
                    gp.player.setHas("Fireball",true);
                    addMessage("Learn new Skill");
                    addMessage("Shooting!");
                    messageCounter=0;
                    scenarioState=3;
                    gp.gameState=gp.playState;
                    break;
            }
        }
    }
    public void pushItems(String items){
        countItems.put(items,countItems.get(items)+1);
    }
    public void addMessage(String text){
        message.add(text);
        messCount.add(0);
    }
}



