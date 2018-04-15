package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Encapsulates an address, including the following properties:
 * <ul>
 * <li>name</li>
 * <li>address line 1</li>
 * <li>address line 2</li>
 * <li>city</li>
 * <li>state or territory</li>
 * <li>zip code</li>
 * </ul>
 * <p>
 * A property may never be null.
 * Unused properties are represented by the empty string
 * </p>
 */
public class Address
{
    /** The maximum number of columns in a line on a mailing label */
    public static final int         MAX_COLS    = 30;
    
    /** The Constant EMPTY. Used whenever an empty string is needed. */
    private static final String     EMPTY       = "";
    
    /** The Constant NULL_ERR. Used in throw statements. */
    private static final String     NULL_ERR    = "Argument may not be null";
    
    /** The Constant STATE_ERR.
     *  Used in throw statements when an invalid state abbreviation is found.
     */
    private static final String     STATE_ERR   = 
        "The state abbreviation appears to be invalid";
    
    /** The constant representing the local newline convention.
     *  Used to make mailing labels.
     */
    private static final String     NEWL        = System.lineSeparator();
    
    /** 
     * The maximum number of columns in a city name on a mailing label.
     * The line containing the city name looks like this:
     * <blockquote>
     * Denver, CO 87122
     * </blockquote>
     * So the maximum length of a city name is the maximum line length,
     * less 2 for the ", " after the name, less 2 for the state abbreviation,
     * less 1 for the space after the state, less 5 for the zip code.
     */
    private static final int        MAX_CITY_COLS   = MAX_COLS - 10;
       
    /** The name. */
    private String  name        = "";
    
    /** The address line 1. */
    private String  addr1       = "";
    
    /** The address line 2. */
    private String  addr2       = "";
    
    /** The city. */
    private String  city        = "";
    
    /** The state. */
    private String  state       = "";
    
    /** The zipcode. */
    private String  zipCode     = "";
    
    /** Array of state abbreviations; only used during initialization. */
    private static final String[]   abbrev  = 
    { 
        "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", 
        "GA", "GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", 
        "MA", "MD", "ME", "MH", "MI", "MN", "MO", "MS", "MT", "NC", 
        "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", 
        "PA", "PR", "PW", "RI", "SC", "SD", "TN", "TX", "UT", "VA", 
        "VI", "VT", "WA", "WI", "WV", "WY"
    };
    
    /** An ordered list of state abbreviations. */
    private static final List<String>   abbreviations  = 
        new ArrayList<>();
    
    // Perform static initialization tasks.
    static
    {
        for ( String str : abbrev )
            abbreviations.add( str );
        abbreviations.sort( (s1, s2) -> s1.compareTo( s2 ) );
    }
    
    /**
     * Instantiates a new address, with all fields unused.
     */
    public Address()
    {
        this( EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY );
    }
    
    /**
     * Instantiates a new address using the name property;
     * all other properties are unused..
     *
     * @param name the name
     */
    public Address( String name )
    {
        this( name, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY );
    }
    
    /**
     * Instantiates a new address with all properties
     * except <em>address line 2</em>.
     *
     * @param name the name
     * @param addr1 the street 1
     * @param city the city
     * @param state the state
     * @param zipcode the zipcode
     */
    public Address(
        String name,
        String addr1,
        String city, 
        String state,
        String zipcode
    )
    {
        this( name, addr1, EMPTY, city, state, zipcode );
    }

    /**
     * Instantiates a new address; all properties are set as indicated
     *
     * @param name the name
     * @param addr1 the street 1
     * @param addr2 the street 2
     * @param city the city
     * @param state the state
     * @param zipCode the zip code
     */
    public Address(
        String name,
        String addr1,
        String addr2,
        String city, 
        String state,
        String zipCode
    )
    {
        setName( name );
        setAddr1( addr1 );
        setAddr2( addr2 );
        setCity( city );
        setState( state );
        setZipCode( zipCode );
    }
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     * @throws IllegalArgumentException if name is null
     */
    public void setName(String name) throws IllegalArgumentException
    {
        if ( name == null )
            throw new IllegalArgumentException( NULL_ERR );
        this.name = name;
    }

    /**
     * Gets the street 1.
     *
     * @return the street 1
     */
    public String getAddr1()
    {
        return addr1;
    }

    /**
     * Sets the street 1.
     *
     * @param addr1 the new address line 1
     * @throws IllegalArgumentException if <em>addr1</em> is null
     */
    public void setAddr1(String addr1) throws IllegalArgumentException
    {
        if ( addr1 == null )
            throw new IllegalArgumentException( NULL_ERR );
        this.addr1 = addr1;
    }

    /**
     * Gets the street 2.
     *
     * @return the street 2
     */
    public String getAddr2()
    {
        return addr2;
    }

    /**
     * Sets the street 2.
     *
     * @param addr2 the new address line 2
     * @throws IllegalArgumentException  if <em>addr2</em> is null
     */
    public void setAddr2(String addr2) throws IllegalArgumentException
    {
        if ( addr2 == null )
            throw new IllegalArgumentException( NULL_ERR );
        this.addr2 = addr2;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city the new city
     * @throws IllegalArgumentException if <em>city</em> is null
     */
    public void setCity(String city) throws IllegalArgumentException
    {
        if ( city == null )
            throw new IllegalArgumentException( NULL_ERR );
        this.city = city;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public String getState()
    {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state the new state
     * @throws IllegalArgumentException if <em>state</em> is null,
     *       or if it is not a valid state or territory abbreviation.
     */
    public void setState(String state) throws IllegalArgumentException
    {
        if ( state == null )
            throw new IllegalArgumentException( NULL_ERR );
        if ( !state.isEmpty() )
            if ( Collections.binarySearch( abbreviations, state ) < 0 )
                throw new IllegalArgumentException( STATE_ERR + ": " + state );
        this.state = state;
    }

    /**
     * Gets the zip code.
     *
     * @return the zip code
     */
    public String getZipCode()
    {
        return zipCode;
    }

    /**
     * Sets the zipcode.
     *
     * @param zipCode the new zip code
     * @throws IllegalArgumentException if <em>zipCode</em> is null
     */
    public void setZipCode(String zipCode) throws IllegalArgumentException
    {
        if ( zipCode == null )
            throw new IllegalArgumentException( NULL_ERR );
        this.zipCode = zipCode;
    }
    
    /**
     * Gets a mailing label formatted as follows:
     * <blockquote>
     * name
     * addr1
     * addr2
     * city, sa zzzzz
     * </blockquote>
     * where <em>sa</em> is the state abbreviation,
     * and <em>zzzzz</em> is the 5-digit zip code.
     * If addr2 is unused it is omitted.
     * No line will exceed 30 characters;
     * if <em>name, addr1</em> or <em>addr2</em> exceed
     * 30 characters they will be truncated;
     * if <em>city, sa zzzzz</em> exceeds 30 characters,
     * <em>city</em> will be truncated.
     *
     * @return the mailing label
     */
    public String getMailingLabel()
    {
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( getSubstring( name, MAX_COLS ) ).append( NEWL );
        bldr.append( getSubstring( addr1, MAX_COLS ) ).append( NEWL );
        if ( !addr2.isEmpty() )
            bldr.append( getSubstring( addr2, MAX_COLS ) ).append( NEWL );
        bldr.append( getSubstring( city, MAX_CITY_COLS ) )
            .append( ", " ).append( state ).append( " " ).append( zipCode );
        
        return bldr.toString();
    }
    
    private String getSubstring( String str, int maxCols )
    {
        int     len     = str.length();
        String  rval    = len <= maxCols ? str : str.substring( 0, maxCols );
        return rval;
    }
}
