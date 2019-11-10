<p><strong><u>Objective:</u></strong></p>
<p><em>The objective of this task is design, implement and test a thread-safe 'forgetting map'.A 'forgetting' map should hold associations between a ‘key’ and some ‘content’. It should implement at least two methods: &nbsp;</em></p>
<p><em>1. add (add an association)</em></p>
<p><em>2. find (find content using the specified key).</em></p>
<p><em>It should hold as many associations as it can, but no more than x associations at any time, with x being a parameter passed to the constructor. Associations that are least used (in a sense of 'find') are removed from the map as needed.</em></p>
<p>
  <br>
</p>
<p><strong><u>Solution:</u></strong></p>
<p><strong>Design description:</strong></p>
<p> The solution is implemented with an array data structure.  This array is used inside an object to store the elements. Elements are added to the top of the array and existing elements are pushed down the array. When elements reaches the end of the array, they are discarded. This behaviour of the object gives the ability to forget old and unused elements. When an element is searched (find) on the array, the element is swapped  to the top location of the array. All elements which are on the top of the searched element are moved one place down.</p>
<p>To facilitate thread safety, methods to put and get element are made synchronized. A combine synchronized put method is given which first adds element and then makes a find call with the key to make sure that the value is not modified immediately by other thread and the call is guaranteed that the values is not altered or deleted during the call. </p>         
<p> This is one of the solutions out of many possible solutions. 
This solution is implemented as it uses very basic data structure and very simple. It does not use any heavy operations like object copy or object comparison. It is relatively less memory consumed solution. It may have higher computational time for very big arrays.</p>           
<p>Few jUnit test classes are provided to test the desired behaviour.  There is a separate test which ensures thread safety.
</p>
<p>
  <a href="https://github.com/anjansharmasid/forgettingmap/blob/master/src/main/java/com/ori/fm/design/HotArray.java" rel="noopener noreferrer" target="_blank">HotArray</a>: This class is used as the design template. It has a fix size, enables addition of elements in the front, discards old elements to accommodate new elements once its max size is reached. The class is not used in the construction of the Map. This class is provided to show the design principle only.
</p>
<p>
  <br>
</p>
<p><u><strong>Classes used in the solution:</strong></u></p>
<p>
  <a href="https://github.com/anjansharmasid/forgettingmap/blob/master/src/main/java/com/ori/fm/impl/KeyValuePair.java" rel="noopener noreferrer" target="_blank">KeyValuePair</a> : &nbsp;Key Value Pair object to be used inside the Map.
</p>
<p>
  <a href="https://github.com/anjansharmasid/forgettingmap/blob/master/src/main/java/com/ori/fm/impl/ForgettingMap.java" rel="noopener noreferrer" target="_blank">ForgettingMap</a>: This class implements the forgetting map solution.
</p>
<p>
  <br>
</p>
<p><strong><u>Junit Test Classes:</u></strong></p>
<p>
  <a href="https://github.com/anjansharmasid/forgettingmap/blob/master/src/test/java/com/ori/fm/design/HotArrayTest.java" rel="noopener noreferrer" target="_blank">HotArrayTest:</a> This class is provided to test the &nbsp;design principle.
</p>
<p>
  <a href="https://github.com/anjansharmasid/forgettingmap/blob/master/src/test/java/com/ori/fm/impl/ForgettingMapTest.java" rel="noopener noreferrer" target="_blank">ForgettingMapTest:</a> This jUnit class tests all declared methods, constructor and general unit &nbsp;behaviour of the ForgettingMap class.
</p>
<p>
  <a href="https://github.com/anjansharmasid/forgettingmap/blob/master/src/test/java/com/ori/fm/impl/ThreadSafeTest.java" rel="noopener noreferrer" target="_blank">ThreadSafeTest:</a> This test class is not a jUnit test class and can be tested by running the main method. The test given in this class demonstrates the thread safety of &nbsp;public synchronized &nbsp;V put(K key, V value) method.
</p>
<p>
  <br>
</p>
<p><em>The classes are &nbsp;built with Apache Maven 3.5.0, java version &nbsp;1.8.0_121 and junit 4.12.</em></p>
<p>&nbsp;&nbsp;</p>
<p>
  <br>
</p>
