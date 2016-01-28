package user.example.com.myfileexplorer;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by user1 on 2016/1/28.
 */
enum PathType {
    DIR, FILE, PARENT_DIR
}

public class PathDataSource {
    private ArrayList<PathInfo> paths = null;
    Context context;
    String pwd;
    
    public PathDataSource(Context context){
        this.context=context;
        paths=new ArrayList<PathInfo>();
    }
    public int size(){
        return paths.size();
    }
    public String getPwd(){
        return pwd;
    }
    
    public PathInfo get(int index){
        return paths.get(index);
    }
    
    
    public ArrayList<PathInfo> getAll(){
        return paths;
    }
    public boolean isEmpty(){
        return paths.isEmpty();
    }
    public void udpatePaths(String rootPath){
        File file=new File(rootPath);
        if(file.isFile()) return;
        pwd=rootPath;
        paths.clear();
        PathInfo parentPathInfo=new PathInfo();
        parentPathInfo.path=rootPath;
        parentPathInfo.pathType=PathType.PARENT_DIR;
        paths.add(parentPathInfo);
        
        File[] files=file.listFiles();
        for (File f:files) {
            PathInfo pathInfo=new PathInfo();
            pathInfo.path=f.getAbsolutePath();
            if(f.isFile()){
                pathInfo.pathType=PathType.FILE;
            }else if(f.isDirectory()){
                pathInfo.pathType=PathType.DIR;
            }
            paths.add(pathInfo);
        }

    }


    public class PathInfo {
        public PathType pathType;
        public String path;
    }
}
