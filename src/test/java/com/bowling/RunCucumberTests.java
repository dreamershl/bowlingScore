package com.bowling;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.cucumber.guice.CucumberModules;
import io.cucumber.guice.InjectorSource;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasspathResource("com/bowling")
public class RunCucumberTests implements InjectorSource {
  private final Injector injector = Guice.createInjector(CucumberModules.createScenarioModule());

  @Override
  public Injector getInjector() {
    return injector;
  }
}
