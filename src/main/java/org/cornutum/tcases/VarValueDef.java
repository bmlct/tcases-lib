//////////////////////////////////////////////////////////////////////////////
// 
//                    Copyright 2012, Cornutum Project
//                             www.cornutum.org
//
//////////////////////////////////////////////////////////////////////////////

package org.cornutum.tcases;

import org.cornutum.tcases.conditions.ICondition;
import org.cornutum.tcases.util.ToString;
import static org.cornutum.tcases.DefUtils.*;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.xml.sax.Attributes;

import java.util.Arrays;
import java.util.Collection;

/**
 * Defines the properties of a value for an {@link IVarDef input variable}.
 *
 */
public class VarValueDef extends Conditional
  {
  /**
   * Defines the type of an input value.
   *
   */
  public enum Type
    {
    /**
     * A valid input value
     */
    VALID( true),

    /**
     * An invalid input value
     */
    FAILURE( false),

    /**
     * A valid input value that need not appear in multiple test case combinations.
     */
    ONCE( true);

    Type( boolean valid)
      {
      valid_ = valid;
      }

    /**
     * Returns if this type of value is a valid member of the variable input domain.
     */
    public boolean isValid()
      {
      return valid_;
      }

    private boolean valid_;
    }

  /**
   * Creates a new VarValueDef object.
   */
  public VarValueDef()
    {
    this( null, null);
    }

  /**
   * Creates a new VarValueDef object.
   */
  public VarValueDef( String name, Attributes attributes)
    {
    this( name, attributes, Type.VALID);
    }

  /**
   * Creates a new VarValueDef object.
   */
  public VarValueDef( String name, Attributes attributes, Type type)
    {
    setName( name);
    setType( type);
      String vartype = "";
      String varsignificance = "";
      String valuetype = "";
      String teststatus = "";
      String testimportance = "";
      String expectedresult = "";
      if(attributes != null){
        vartype = attributes.getValue("vartype");
        varsignificance = attributes.getValue("varsignificance");
        valuetype = attributes.getValue("valuetype");
        teststatus = attributes.getValue("teststatus");
        testimportance = attributes.getValue("testimportance");
        expectedresult = attributes.getValue("expectedresult");
      }
      setVarType(vartype);
      setVarSignificance(varsignificance);
      setValueType(valuetype);
      setTestStatus(teststatus);
      setTestImportance(testimportance);
      setExpectedResult(expectedresult);
      setCondition(ICondition.ALWAYS);
    setProperties( (PropertySet) null);
    }

  /**
   * Changes the name of this value.
   */
  public void setName( String name)
    {
    assertIdentifier( name);
    name_ = name;
    }

  /**
   * Returns the name of this value.
   */
  public String getName()
    {
    return name_;
    }

  /**
   * Changes the type of this value.
   */
  public void setType( Type type)
    {
    type_ = type;
    }

  /**
   * Returns the type of this value.
   */
  public Type getType()
    {
    return type_;
    }

  /**
   * Changes the type of this variable.
   */
  public void setVarType( String varType)
    {
      varType_ = varType;
    }

  /**
   * Returns the type of this variable.
   */
  public String getVarType()
    {
    return varType_;
    }

  /**
   * Changes the significance of this variable.
   */
  public void setVarSignificance( String varSignificance)
    {
      varSignificance_ = varSignificance;
    }

  /**
   * Returns the significance of this variable.
   */
  public String getVarSignificance()
    {
    return varSignificance_;
    }

    /**
     * Changes the type of this value.
     */
    public void setValueType( String valuetype)
    {
      valuetype_ = valuetype;
    }

    /**
     * Returns the type of this value.
     */
    public String getValueType()
    {
      return valuetype_;
    }

    /**
     * Changes the status of this test.
     */
    public void setTestStatus( String teststatus)
    {
      teststatus_ = teststatus;
    }

    /**
     * Returns the status of this test.
     */
    public String getTestStatus()
    {
      return teststatus_;
    }

    /**
     * Changes the Importance of this test.
     */
    public void setTestImportance( String testimportance)
    {
      testimportance_ = testimportance;
    }

    /**
     * Returns the Importance of this test.
     */
    public String getTestImportance()
    {
      return testimportance_;
    }

    /**
     * Changes the expected result of this test.
     */
    public void setExpectedResult( String expectedresult)
    {
      expectedresult_ = expectedresult;
    }

    /**
     * Returns the expected result of this test.
     */
    public String getExpectedResult()
    {
      return expectedresult_;
    }

    /**
   * Returns if this value is a valid member of the variable input domain.
   */
  public boolean isValid()
    {
    return getType().isValid();
    }

  /**
   * Changes the set of test case properties contributed by this value.
   */
  public void setProperties( Collection<String> properties)
    {
    properties_ = new PropertySet();
    addProperties( properties);
    }

  /**
   * Changes the set of test case properties contributed by this value.
   */
  public void setProperties( PropertySet properties)
    {
    properties_ = new PropertySet();
    addProperties( properties);
    }

  /**
   * Returns the set of test case properties contributed by this value.
   */
  public PropertySet getProperties()
    {
    return properties_;
    }

  /**
   * Adds to the set of test case properties contributed by this value.
   */
  public VarValueDef addProperties( Collection<String> properties)
    {
    if( properties != null)
      {
      assertPropertyIdentifiers( properties);
      getProperties().addAll( properties);
      }

    return this;
    }

  /**
   * Adds to the set of test case properties contributed by this value.
   */
  public VarValueDef addProperties( PropertySet properties)
    {
    if( properties != null)
      {
      addProperties( IteratorUtils.toList( properties.getProperties()));
      }

    return this;
    }

  /**
   * Adds to the set of test case properties contributed by this value.
   */
  public VarValueDef addProperties( String ... properties)
    {
    return addProperties( Arrays.asList( properties));
    }

  @SuppressWarnings("deprecation")
  public boolean equals( Object object)
    {
    VarValueDef other =
      object != null && object.getClass().equals( getClass())
      ? (VarValueDef) object
      : null;

    return
      other != null
      && ObjectUtils.equals( other.getName(), getName())
      && ObjectUtils.equals( other.getType(), getType());
    }

  @SuppressWarnings("deprecation")
  public int hashCode()
    {
    return
      getClass().hashCode()
      ^ ObjectUtils.hashCode( getName())
      ^ ObjectUtils.hashCode( getType());
    }
  
  public String toString()
    {
    return
      ToString.getBuilder( this)
      .append( getName())
      .toString();
    }

  /**
   * Returns true if the given value is the standard {@link #NA "not applicable"} value.
   */
  public static boolean isNA( VarValueDef value)
    {
    return value == NA;
    }

  /**
   * The standard "not applicable" value. This value is valid for any variable that is
   * "optional", i.e. that (has an ancestor that) defines a condition.
   */
  public static final VarValueDef NA = new VarValueDef( "NA", null);

  private String name_;
  private Type type_;
  private String varType_;
  private String varSignificance_;
  private String valuetype_;
  private String teststatus_;
  private String testimportance_;
  private String expectedresult_;
  private PropertySet properties_;
  }

