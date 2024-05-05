package com.server.demo.field;

import com.server.demo.table.Table;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Field.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Field_ {

	
	/**
	 * @see com.server.demo.field.Field#name
	 **/
	public static volatile SingularAttribute<Field, String> name;
	
	/**
	 * @see com.server.demo.field.Field#id
	 **/
	public static volatile SingularAttribute<Field, Long> id;
	
	/**
	 * @see com.server.demo.field.Field
	 **/
	public static volatile EntityType<Field> class_;
	
	/**
	 * @see com.server.demo.field.Field#table
	 **/
	public static volatile SingularAttribute<Field, Table> table;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String TABLE = "table";

}

