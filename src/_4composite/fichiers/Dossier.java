package _4composite.fichiers;

import java.util.ArrayList;
import java.util.List;

public class Dossier implements IFile {
  private List<IFile> iFiles = new ArrayList<>();

  public List<IFile> getIFiles() {
    return iFiles;
  }

  @Override
  public int getTaille() {
    int taille = 0;
    for (IFile iFile : iFiles) {
      taille += iFile.getTaille();
    }
    return taille;
  }
}
