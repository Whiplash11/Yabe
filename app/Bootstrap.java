import models.*;
import play.*;
import play.jobs.*;
import play.test.Fixtures;

@OnApplicationStart//这个注释告诉Play，在应用程序启动时同步运行这个工作
public class Bootstrap extends Job{
    @Override
    public void doJob(){
        //check if the database is empty
        if (User.count() == 0){
            Fixtures.loadModels("initial-data.yml");
        }
    }

}
