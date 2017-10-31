package application.ebs.commander;

import application.ebs.handlers.CommandHandler;
import application.ebs.models.GameCommandModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public abstract class AbstractCommand {

    protected CommandHandler commandHandler;

    protected void resolveCommand(GameCommandModel command) {

    }
}
