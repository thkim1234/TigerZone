package tools;

import java.util.*;

public class RotatedIterator<T> implements Iterable<T>{
  private T[] list;
  private int size;
  private int max;
  private int rotation;

  public RotatedIterator(T[] list, int rotation){
    this.size = list.length;
    this.rotation = rotation-1;
    this.list = list;
  }

  public Iterator<T> iterator() {
      Iterator<T> it = new Iterator<T>() {

          private int currentIndex = rotation;
          boolean looped = false;

          public boolean hasNext() {
              return looped ? currentIndex < rotation : currentIndex >= rotation;
          }

          public T next() {
              currentIndex++;
              if(currentIndex >= size){
                looped = true;
                currentIndex = 0;
              }
              return list[currentIndex];
          }

          public void remove() {
              throw new UnsupportedOperationException();
          }
      };
      return it;
  }
}
