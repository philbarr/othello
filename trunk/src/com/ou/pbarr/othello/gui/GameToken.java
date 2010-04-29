package com.ou.pbarr.othello.gui;

public class GameToken {

    private Colour gameColour;
    private int xPosition;
    private int yPosition;
    
    public enum Colour
    {
        LIGHT,
        DARK,
        GHOST
    }

    public GameToken(Colour gameColour, int xPosition, int yPosition) {
        super();
        this.gameColour = gameColour;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    
    public void setGameColour(Colour gameColour)
    {
        this.gameColour = gameColour;
    }
    
    public Colour getGameColour() {
        return gameColour;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

}
