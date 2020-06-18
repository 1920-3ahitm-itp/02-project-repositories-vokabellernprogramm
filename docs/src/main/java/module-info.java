module vocabulary {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;
  requires org.apache.derby.client;
  requires org.apache.derby.commons;
  requires org.apache.derby.tools;
  requires org.mybatis;

  opens at.htl.vocabulary.view to javafx.fxml;
  exports at.htl.vocabulary.view;
  exports at.htl.vocabulary.controller;
  exports at.htl.vocabulary.model;
  exports at.htl.vocabulary.database;
}