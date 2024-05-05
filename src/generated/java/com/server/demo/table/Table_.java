package com.server.demo.table;

import com.server.demo.field.Field;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Table.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Table_ {

	
	/**
	 * @see com.server.demo.table.Table#name
	 **/
	public static volatile SingularAttribute<Table, String> name;
	
	/**
	 * @see com.server.demo.table.Table#id
	 **/
	public static volatile SingularAttribute<Table, Long> id;
	
	/**
	 * @see com.server.demo.table.Table#fields
	 **/
	public static volatile SetAttribute<Table, Field> fields;
	
	/**
	 * @see com.server.demo.table.Table
	 **/
	public static volatile EntityType<Table> class_;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String FIELDS = "fields";

}

