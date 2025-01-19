package com.sierra.skyTeam.model;

/**
 * Die Klasse {@code Airplane} repräsentiert das Flugzeugmodell im Spiel.
 * Sie enthält verschiedene Komponenten des Flugzeugs, wie Engine, Axis, Brakes,
 * Landing-Gear und Flaps. Außerdem verwaltet sie die Anflugposition des Flugzeugs.
 */
public class Airplane{
    private int approachPosition = 0;

    private GameModel gameModel;
    private final AxisModel axisModel;
    private final Engine engine;
    private final Brakes brakes;
    private final LandingGear landingGear;
    private final Flaps flaps;
    private final Concentration concentration;

    /**
     * Konstruktor für Airplane. Initialisiert die Höhe und die Anflugposition.
     * Erstellt Instanzen der Flugzeugkomponenten wie Engine, Axis, Brakes,
     * Landing-Gear, Flaps und Coffee(Concentration).
     */
    public Airplane(){
        this.approachPosition = 0;
        engine = new Engine(this);
        axisModel = new AxisModel(this);
        brakes = new Brakes(this);
        landingGear = new LandingGear(this);
        flaps = new Flaps(this);
        concentration = new Concentration();
    }

    /**
     * Setzt das {@code GameModel}, mit dem das Flugzeug verknüpft ist.
     *
     * @param gameModel Das GameModel, mit dem das Airplane verbunden ist.
     */
    public void setGame(GameModel gameModel){
        this.gameModel = gameModel;
    }
    /**
     * Gibt das aktuelle {@code GameModel} zurück.
     *
     * @return Das mit dem Flugzeug verknüpfte GameModel.
     */
    public GameModel getGame(){
        return gameModel;
    }

    /**
     * Gibt das Axis-modell des Flugzeugs zurück.
     *
     * @return Das AxisModel.
     */
    public AxisModel getAxis() {
        return axisModel;
    }

    /**
     * Gibt den Engine des Flugzeugs zurück.
     *
     * @return Der Engine-Model.
     */
    public Engine getEngine(){
        return engine;
    }

    /**
     * Gibt die Brakes des Flugzeugs zurück.
     *
     * @return Die Brake-Model.
     */
    public Brakes getBrakes() {
        return brakes;
    }

    /**
     * Gibt das LandingGear des Flugzeugs zurück.
     *
     * @return Das LandingGear-Model.
     */
    public LandingGear getLandingGear() {
        return landingGear;
    }

    /**
     * Gibt die Flaps des Flugzeugs zurück.
     *
     * @return Die Flap-Model.
     */
    public Flaps getFlaps(){
        return flaps;
    }

    /**
     * Gibt die Coffee(Concentration) des Flugzeugs zurück.
     *
     * @return Die Concentration-Model.
     */
    public Concentration getConcentration() {
        return concentration;
    }

    /**
     * Gibt die aktuelle Anflugposition des Flugzeugs zurück.
     *
     * @return Die Anflugposition.
     */
    public int getApproachPosition(){
        return approachPosition;
    }
    /**
     * Setzt die Anflugposition des Flugzeugs.
     *
     * @param approachPosition Die neue Anflugposition.
     */
    public void setApproachPosition(int approachPosition){
        this.approachPosition = approachPosition;
    }

    /**
     * Gibt die aktuelle Flughöhe des Flugzeugs zurück.
     *
     * @return Die Flughöhe.
     */
    public int getAltitude(){
        return gameModel.getAltitudeTrack().getCurrentAltitude();
    }
}
