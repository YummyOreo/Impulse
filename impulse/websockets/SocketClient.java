package impulse.websockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import impulse.Impulse;
import impulse.util.cache.CacheIsUser;
import impulse.util.websockets.Connect;

public class SocketClient
{
    public static String urlString;
    
    private static String userCheck = "/isUser/";
    private static String updateCheck = "/updateCheck/";
    private static String capeCheck = "/capeCheck/";
    private static String createUser = "/addUser/";
    private static String createCosmetic = "/addCosmetic/";
    private static String deleteCosmetic = "/removeCosmetic/";
    private static String checkCosmetic = "/checkCosmetic/";
    private static String remove = "/remove/";
    private static String colon = "%3A";
    
    public static void main(final String[] args) {
        while (true) {}
    }
    
    public static void changeServer(final String url1) {
        SocketClient.urlString = url1;
    }
    
    public static String sendRequest(final String args) {
        final StringBuffer responseContent = new StringBuffer();
        try {
            final URL url = new URL(String.valueOf(SocketClient.urlString) + "/");
            final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            final int status = connection.getResponseCode();
            if (status > 299) {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                System.out.println("Error connecting to server " + status);
                return "error";
            }
            final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
            System.out.println("connecting to server");
            return responseContent.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error connecting to server");
            return "error";
        }
    }
    
    public static boolean isUser(final String username) {
        if (CacheIsUser.CONSTANT_MAP_IsUser.get(username) == null) {
        	if (!Connect.INSTANCE.enabled) {
        		CacheIsUser.CONSTANT_MAP_IsUser.put(username, false);
        		CacheIsUser.CONSTANT_MAP_TIME_IsUser.put(username, (int) System.currentTimeMillis());
        		return false;
        	}
            final StringBuffer responseContent = new StringBuffer();
            try {
                final URL url = new URL(String.valueOf(SocketClient.urlString) + userCheck + username);
                final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                final int status = connection.getResponseCode();
                if (status > 299) {
                    final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }
                    reader.close();
                    CacheIsUser.CONSTANT_MAP_IsUser.put(username, false);
                    CacheIsUser.CONSTANT_MAP_TIME_IsUser.put(username, (int)System.currentTimeMillis());
                    return false;
                }
                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                if (responseContent.toString().equals("\"true\"")) {
                    CacheIsUser.CONSTANT_MAP_IsUser.put(username, true);
                    CacheIsUser.CONSTANT_MAP_TIME_IsUser.put(username, (int)System.currentTimeMillis());
                    return true;
                }
                CacheIsUser.CONSTANT_MAP_IsUser.put(username, false);
                CacheIsUser.CONSTANT_MAP_TIME_IsUser.put(username, (int)System.currentTimeMillis());
                return false;
            }
            catch (IOException e) {
                e.printStackTrace();
                CacheIsUser.CONSTANT_MAP_IsUser.put(username, false);
                CacheIsUser.CONSTANT_MAP_TIME_IsUser.put(username, (int)System.currentTimeMillis());
                return false;
            }
        }
        return CacheIsUser.CONSTANT_MAP_IsUser.get(username);
    }
    
    public static boolean checkUpdate() {
    	if (!Connect.INSTANCE.enabled) return false;
        final StringBuffer responseContent = new StringBuffer();
        try {
            final URL url = new URL(String.valueOf(SocketClient.urlString) + updateCheck + Impulse.INSTANCE.VERSION);
            final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            final int status = connection.getResponseCode();
            if (status > 299) {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                return false;
            }
            final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
            return responseContent.toString().equals("\"true\"");
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean checkCape(final String name) {
    	if (!Connect.INSTANCE.enabled) return true;
        final StringBuffer responseContent = new StringBuffer();
        try {
            final URL url = new URL(String.valueOf(SocketClient.urlString) + capeCheck + name);
            final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            final int status = connection.getResponseCode();
            if (status > 299) {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                return false;
            }
            final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
            return responseContent.toString().equals("\"true\"");
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static String addUser(final String name) {
    	if (!Connect.INSTANCE.enabled) return "error";
        final StringBuffer responseContent = new StringBuffer();
        try {
            final URL url = new URL(String.valueOf(SocketClient.urlString) + createUser + name);
            final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            final int status = connection.getResponseCode();
            if (status > 299) {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                return "error";
            }
            final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
            return responseContent.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
   
    public static void addCosmetic(final String name, String cosmetic) {
    	if (!Connect.INSTANCE.enabled) return;
        final StringBuffer responseContent = new StringBuffer();
        try {
            cosmetic = cosmetic.replace("[", "%5B");
            cosmetic = cosmetic.replace("[", "%5D");
            final URL url = new URL(String.valueOf(SocketClient.urlString) + createCosmetic + name + colon + cosmetic);
            final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            final int status = connection.getResponseCode();
            if (status > 299) {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            else {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void removeCosmetic(final String name, String cosmetic) {
    	if (!Connect.INSTANCE.enabled) return;
        final StringBuffer responseContent = new StringBuffer();
        try {
            cosmetic = cosmetic.replace("[", "%5B");
            cosmetic = cosmetic.replace("[", "%5D");
            final URL url = new URL(String.valueOf(SocketClient.urlString) + deleteCosmetic + name + colon + cosmetic);
            final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            final int status = connection.getResponseCode();
            if (status > 299) {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            else {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String checkUserCosmetic(final String name, String cosmetic) {
    	if (!Connect.INSTANCE.enabled) return "false";
        final StringBuffer responseContent = new StringBuffer();
        try {
            cosmetic = cosmetic.replace("[", "%5B");
            cosmetic = cosmetic.replace("[", "%5D");
            final URL url = new URL(String.valueOf(SocketClient.urlString) + checkCosmetic + name + colon + cosmetic);
            final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            final int status = connection.getResponseCode();
            if (status > 299) {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                return "false";
            }
            final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
            return responseContent.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }
    
    public static void removeUser(final String username) {
        final StringBuffer responseContent = new StringBuffer();
        try {
            final URL url = new URL(String.valueOf(SocketClient.urlString) + remove + username);
            final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            final int status = connection.getResponseCode();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
