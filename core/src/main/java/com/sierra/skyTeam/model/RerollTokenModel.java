package com.sierra.skyTeam.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.view.RerollToken;

import java.util.ArrayList;
import java.util.List;

public class RerollTokenModel {

    private final List<RerollToken> rerollTokens;
    private int index;

    public RerollTokenModel() {
        rerollTokens = new ArrayList<>();
        index = -1;
    }

    public void generateToken(float x, float y) {
        RerollToken rerollToken = new RerollToken();
        rerollToken.setPosition(x, y);
        rerollTokens.add(rerollToken);
    }

    public void addToken() {
        float x = 355;
        float y = 562;
        for(int i = 0; i < rerollTokens.size(); i++){
            RerollToken rerollToken = rerollTokens.get(i);
            if(!rerollToken.isUsed() && !rerollToken.isOnBoard()){
                //only applies new positioning if the previous token was rendered and not used
                for(int j = 0; j < i; j++){
                    RerollToken prevToken = rerollTokens.get(j);
                    if(prevToken.isOnBoard() && !prevToken.isUsed()) {
                        x += 3;
                        y += 10;
                    }
                }

                rerollToken.setPosition(x, y);
                rerollToken.setOnBoard(true);
                index = i;
                break;
            }
        }
    }

    public void markTokenAsUsed() {
        if(index >= 0 && index < rerollTokens.size()) {
            RerollToken rerollToken = rerollTokens.get(index);
            rerollToken.setUsed();
            rerollToken.setOnBoard(false);
            index = -1;
        }
    }

    public List<Boolean> tokensOnBoard() {
        List<Boolean> tokensOnBoard = new ArrayList<>();
        for (RerollToken rerollToken : rerollTokens) {
            tokensOnBoard.add(rerollToken.isOnBoard());
        }
        return tokensOnBoard;
    }

    public void draw(SpriteBatch batch) {
        for(RerollToken rerollToken : rerollTokens){
            rerollToken.draw(batch);
        }
    }
}
