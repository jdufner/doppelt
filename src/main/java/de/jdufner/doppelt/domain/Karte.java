package de.jdufner.doppelt.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import de.jdufner.doppelt.domain.Karte.ListOfElementUserType;
import de.jdufner.doppelt.utils.NumberUtil;

/**
 *
 * @author Jürgen Dufner
 * @since 1.0
 */
@Entity
@Table(name = "karte")
@TypeDefs({ @TypeDef(name = ListOfElementUserType.NAME, typeClass = ListOfElementUserType.class) })
public class Karte {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "kart_id", unique = true, nullable = false)
  private Long id;
  //  @OneToMany(cascade = CascadeType.ALL)
  //  @JoinTable(name = "kartenelemente", //
  //  joinColumns = { @JoinColumn(name = "kael_kart_id", referencedColumnName = "kart_id") }, //
  //  inverseJoinColumns = { @JoinColumn(name = "kael_elmt_id", referencedColumnName = "elmt_id") })
  //  @OrderColumn(name = "kael_order")
  @Column(name = "kart_elemente")
  @Type(type = ListOfElementUserType.NAME)
  private List<Element> elemente;

  public Karte() {
  }

  public Karte(final List<Element> elemente) {
    this.elemente = elemente;
  }

  public void mischeElemente() {
    List<Element> gemischteElemente = new ArrayList<Element>();
    int size = elemente.size();
    for (int i = 0; i < size; i++) {
      gemischteElemente.add(elemente.remove(NumberUtil.liefereZufallszahl(0, elemente.size())));
    }
    elemente = gemischteElemente;
  }

  public List<Element> getElemente() {
    return elemente;
  }

  public void setElemente(final List<Element> elemente) {
    this.elemente = elemente;
  }

  @Override
  public String toString() {
    return "Karte [elemente=" + elemente + "]";
  }

  public static class ListOfElementUserType implements UserType {

    public static final String NAME = "ListOfElement";

    @Override
    public int[] sqlTypes() {
      return new int[] { Types.VARCHAR };
    }

    @Override
    public Class<?> returnedClass() {
      return List.class;
    }

    @Override
    public boolean equals(final Object x, final Object y) throws HibernateException {
      if (x == null || y == null) {
        return false;
      } else {
        return x.equals(y);
      }
    }

    @Override
    public int hashCode(final Object x) throws HibernateException {
      return x.hashCode();
    }

    @Override
    public Object nullSafeGet(final ResultSet rs, final String[] names, final SessionImplementor session, final Object owner)
        throws HibernateException, SQLException {
      String elementsAsString = rs.getString(names[0]);
      return Element.buildList(elementsAsString);
    }

    @Override
    public void nullSafeSet(final PreparedStatement st, final Object value, final int index, final SessionImplementor session)
        throws HibernateException, SQLException {
      st.setString(index, Element.buildString((List<Element>) value));
    }

    @Override
    public Object deepCopy(final Object value) throws HibernateException {
      return value;
    }

    @Override
    public boolean isMutable() {
      return true;
    }

    @Override
    public Serializable disassemble(final Object value) throws HibernateException {
      return (Serializable) value;
    }

    @Override
    public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
      return cached;
    }

    @Override
    public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
      return original;
    }
  }

}
