public class CoPilot extends Players {
    public CoPilot(Game game){
        super(game);
        this.radioSlots = 2;
        this.radioPlayer = new Field[radioSlots];
        for (int i = 0; i < radioSlots; i++) {
            radioPlayer[i] = new Field("Radio Copilot");
        }
    }


    /*@Override
    public void setRadio(int diceValue) {
        if (getRadioSlots() < 2) {
            super.setRadio(diceValue);
        } else System.out.println(this.getClass().getSimpleName() + " cannot place more dice in the Radio slot");
    }*/
}
