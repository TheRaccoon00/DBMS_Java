# Database Management System

Implementation of a mini Database Management System, developed in Java.

## Nuplet Management

To manage and store datas into tables, where are using a Nuplet object.

```java
  Nuplet[] table = new NupletInt[datasetSize];
  byte[] l0 = {0,53,16};
  byte[] l1 = {1,67,11};
  byte[] l2 = {2,43,21};
  byte[] l3 = {3,53,22};
  byte[] l4 = {4,53,16};
  byte[] l5 = {5,34,30};
  table[0] = new NupletInt(l0);
  table[1] = new NupletInt(l1);
  table[2] = new NupletInt(l2);
  table[3] = new NupletInt(l3);
  table[4] = new NupletInt(l4);
  table[5] = new NupletInt(l5);
```

## Table Management

Thanks to the Table object, we can save the table into a file.

```java
  Table table = new TableInt("table_file", nupletSize);
  for(int i=0;i<datasetSize;i++){
    table.put(table1[i]);
    }
```

## Operators

This DMNS system implements only some operators.

Selection:

```java
  // SELECT * FROM table;
  Nuplet[] table_scan = table.fullScan();
```

Restriction:

```java
  RestrictionInt r = new RestrictionInt();

  // SELECT * FROM table WHERE 2nd_element == 53;
  r.egalite(table_scan,1,53);

  // SELECT * FROM table WHERE 2nd_element < 53;
  r.inferieur(table_scan,1,53);

  // SELECT * FROM table WHERE 2nd_element > 53;
  r.superieur(table_scan,1,53);
```

Projection:

```java
  ProjectionImpl p = new ProjectionImpl();
  int[] atts = {0,2};   

  //SELECT att0,att2 FROM table;
  p.project(table_scan, atts);
```

Join:

(Here, I am using 3 differents techniques)

```java
  //SELECT * FROM table1,table2 INNER JOIN table1 ON table1.att1=table2.att2;

  // Nested Loop
  JointureBI jbi = new JointureBI();
  jbi.jointure(table_scan_1,table_scan_2,1,1);

  // Hash Join
  JointureH jhash = new JointureH();
  jhash.jointure(table_scan_1,table_scan_2,1,1);

  //Sort Merge Join
  JointureS jsm = new JointureS();
  jsm.jointure(table_scan_1,table_scan_2,1,1);

```

## Pipeline mode

The pipeline folder contains same operators with a powerfull and non-blocking technique.


## Project

Student project within engineering school, INSA Centre Val de Loire
