package org.example;

import java.util.*;

public abstract class MaterialDAO {
    public abstract Material getMaterial(int materialId);
    public abstract List<Material> getAllMaterials();
    public abstract void addMaterial(Material material);
    public abstract void removeMaterial(int materialId);

}
