/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.constants;

/**
 *
 * @author Reena
 */
public class MonopolyDealConstants {
    //URL

    final public static String LOCAL_HOSTNAME = "http://localhost:8080/";
    final public static String HEROKU_URL = "https://simplechatapp12.herokuapp.com/";
    final public static String SLASH = "/";
    final public static String QUESTION_MARK = "?";
    final public static String AND = "&";
    final public static String EQUAL = "=";
    //Module names
    final static public String LOGIN_MODULE = "login";
    final static public String FIND_OPPONENT_MODULE = "findOpponent";
    //constants
    final public static String USER_NAME = "userName";
    final public static String PLAYER_ID = "playerId";
    final public static String RUNNING_ENVIRONMENT = "running_environment";
    final public static String LOCAL_RUNNING_ENVIRONMENT = "L";
    final public static String WEB_RUNNING_ENVIRONMENT = "W";
    final public static String EMPTY_STRING = "";
    //object types
    final static public String GAME_OBJECT_TYPE = "Game";
    final static public String GAME_STATE_OBJECT_TYPE = "GameState";
    final static public String MESSAGE_OBJECT_TYPE = "Message";
    final static public String PLAYER_OBJECT_TYPE = "Player";
    //returned objects
    final public static String OBJECT_TYPE = "objectType";
    final static public String MESSAGE = "message";
    final static public String ADDITIONAL_STRING_MESSAGE = "additionalStringMessage";
    final static public String FINDING_MATCH = "findingMatch";
    final static public String MESSAGE_TYPE = "messageType";
    //message type
    final static public String MESSAGE_TYPE_INFO = "info";
    final static public String MESSAGE_TYPE_ERROR = "error";
    //info type
    final static public char INFO_FINDING_MATCH = 'S';
    final static public String CONTINUE_MATCH = "ContinueMatch";
    //error type
    final static public char ERROR_INVALID_USERNAME = 'I';
}
