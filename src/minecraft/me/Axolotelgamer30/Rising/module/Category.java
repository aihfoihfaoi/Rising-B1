package me.Axolotelgamer30.Rising.module;

public enum Category {
  COMBAT("Combat"),
  MOVEMENT("Movement"),
  PLAYER("Player"),
  RENDER("Render"),
  MISC("Misc");
  
  public String name;
  
  public int moduleIndex;
  
  Category(String name) {
    this.name = name;
  }
}
