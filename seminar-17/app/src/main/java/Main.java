import adapter.AdapterDemo;
import builder.BuilderDemo;
import command.CommandDemo;
import decorator.DecoratorDemo;
import facade.FacadeDemo;
import factorymethod.FactoryMethodDemo;
import observer.ObserverDemo;
import prototype.PrototypeDemo;
import strategy.StrategyDemo;

public class Main {
    public static void main(String[] args) {
        StrategyDemo.run();
        System.out.println();

        FactoryMethodDemo.run();
        System.out.println();

        BuilderDemo.run();
        System.out.println();

        PrototypeDemo.run();
        System.out.println();

        AdapterDemo.run();
        System.out.println();

        DecoratorDemo.run();
        System.out.println();

        FacadeDemo.run();
        System.out.println();

        ObserverDemo.run();
        System.out.println();

        CommandDemo.run();
    }
}
