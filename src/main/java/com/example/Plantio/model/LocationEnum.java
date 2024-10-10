package com.example.Plantio.model;

public enum LocationEnum {
    LIVING_ROOM("Living Room", "/assets/images/living_room.jpg"),
    BEDROOM("Bedroom", "/assets/images/bedroom.png"),
    KITCHEN("Kitchen", "/assets/images/kitchen.png"),
    CORRIDOR("Corridor", "/assets/images/corridor.png"),
    BATHROOM("Bathroom", "/assets/images/bathroom.png"),
    BALCONY("Balcony", "/assets/images/balcony.png"),;

    private final String displayName;
    private final String backgroundImageUrl;

    LocationEnum(String displayName, String backgroundImageUrl) {
        this.displayName = displayName;
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }
}
