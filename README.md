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
<p>Java Array is used as platform given data structure for the custom Map with forget least use associations behavior.</p>
<p>
  <a href="https://github.com/anjansharmasid/forgettingmap/blob/master/src/main/java/com/ori/fm/design/HotArray.java" rel="noopener noreferrer" target="_blank">HotArray</a>: This class is used as the design template. It has a fix size, enables addition of elements in the front, discards old elements to accommodate new elements once its max size is reached. The class is not used in the construction of the Map. &nbsp;This class is provided to show the design principle only.
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
