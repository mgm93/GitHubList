package com.bsobat.github.di;

import android.app.Application;
import android.content.Context;
import com.bsobat.github.api.GitHubApi;
import com.bsobat.github.dao.GitHubDao;
import com.bsobat.github.di.module.ApiModule;
import com.bsobat.github.di.module.AppModule;
import com.bsobat.github.di.module.DaoModule;
import com.bsobat.github.di.module.NetModule;
import com.bsobat.github.di.module.RepositoryModule;
import com.bsobat.github.repo.GitHubRepository;
import com.bsobat.github.repo.GitHubRepository2;
import com.bsobat.github.viewmodel.MainActivity2ViewModel;
import com.bsobat.github.viewmodel.MainActivityViewModel;
import com.google.gson.Gson;

import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.MembersInjectors;
import dagger.internal.Preconditions;
import java.util.concurrent.Executor;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerAppComponent implements AppComponent {
  private Provider<Gson> provideGsonProvider;

  private Provider<Application> provideApplicationProvider;

  private Provider<Cache> provideHttpCacheProvider;

  private Provider<Interceptor> provideInterceptorProvider;

  private Provider<OkHttpClient> provideOkhttpClientProvider;

  private Provider<Retrofit> provideRetrofitProvider;

  private Provider<GitHubApi> providesCatalogApiProvider;

  private Provider<GitHubDao> provideGitHubDaoProvider;

  private Provider<Executor> provideExecutorProvider;

  private Provider<GitHubRepository> gitHubRepositoryProvider;

  private MembersInjector<MainActivityViewModel> mainActivityViewModelMembersInjector;

  private Provider<GitHubRepository2> gitHubRepository2Provider;

  private MembersInjector<MainActivity2ViewModel> mainActivity2ViewModelMembersInjector;

  private DaggerAppComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideGsonProvider =
        DoubleCheck.provider(NetModule_ProvideGsonFactory.create(builder.netModule));

    this.provideApplicationProvider =
        DoubleCheck.provider(AppModule_ProvideApplicationFactory.create(builder.appModule));

    this.provideHttpCacheProvider =
        DoubleCheck.provider(
            NetModule_ProvideHttpCacheFactory.create(
                builder.netModule, provideApplicationProvider));

    this.provideInterceptorProvider =
        DoubleCheck.provider(NetModule_ProvideInterceptorFactory.create(builder.netModule));

    this.provideOkhttpClientProvider =
        DoubleCheck.provider(
            NetModule_ProvideOkhttpClientFactory.create(
                builder.netModule, provideHttpCacheProvider, provideInterceptorProvider));

    this.provideRetrofitProvider =
        DoubleCheck.provider(
            NetModule_ProvideRetrofitFactory.create(
                builder.netModule, provideGsonProvider, provideOkhttpClientProvider));

    this.providesCatalogApiProvider =
        DoubleCheck.provider(
            ApiModule_ProvidesCatalogApiFactory.create(builder.apiModule, provideRetrofitProvider));

    this.provideGitHubDaoProvider =
        DoubleCheck.provider(
            DaoModule_ProvideGitHubDaoFactory.create(
                builder.daoModule, provideApplicationProvider));

    this.provideExecutorProvider =
        DoubleCheck.provider(
            RepositoryModule_ProvideExecutorFactory.create(builder.repositoryModule));

    this.gitHubRepositoryProvider =
        GitHubRepository_Factory.create(
            providesCatalogApiProvider, provideGitHubDaoProvider, provideExecutorProvider);

    this.mainActivityViewModelMembersInjector =
        MainActivityViewModel_MembersInjector.create(gitHubRepositoryProvider);

    this.gitHubRepository2Provider =
        GitHubRepository2_Factory.create(
            providesCatalogApiProvider, provideGitHubDaoProvider, provideExecutorProvider);

    this.mainActivity2ViewModelMembersInjector =
        MainActivity2ViewModel_MembersInjector.create(gitHubRepository2Provider);
  }

  @Override
  public void inject(MainActivityViewModel viewModelModule) {
    mainActivityViewModelMembersInjector.injectMembers(viewModelModule);
  }

  @Override
  public void inject(MainActivity2ViewModel viewModelModule) {
    mainActivity2ViewModelMembersInjector.injectMembers(viewModelModule);
  }

  @Override
  public void inject(Context content) {
    MembersInjectors.<Context>noOp().injectMembers(content);
  }

  public static final class Builder {
    private NetModule netModule;

    private AppModule appModule;

    private ApiModule apiModule;

    private DaoModule daoModule;

    private RepositoryModule repositoryModule;

    private Builder() {}

    public AppComponent build() {
      if (netModule == null) {
        throw new IllegalStateException(NetModule.class.getCanonicalName() + " must be set");
      }
      if (appModule == null) {
        throw new IllegalStateException(AppModule.class.getCanonicalName() + " must be set");
      }
      if (apiModule == null) {
        this.apiModule = new ApiModule();
      }
      if (daoModule == null) {
        this.daoModule = new DaoModule();
      }
      if (repositoryModule == null) {
        this.repositoryModule = new RepositoryModule();
      }
      return new DaggerAppComponent(this);
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }

    public Builder netModule(NetModule netModule) {
      this.netModule = Preconditions.checkNotNull(netModule);
      return this;
    }

    public Builder repositoryModule(RepositoryModule repositoryModule) {
      this.repositoryModule = Preconditions.checkNotNull(repositoryModule);
      return this;
    }

    public Builder apiModule(ApiModule apiModule) {
      this.apiModule = Preconditions.checkNotNull(apiModule);
      return this;
    }

    public Builder daoModule(DaoModule daoModule) {
      this.daoModule = Preconditions.checkNotNull(daoModule);
      return this;
    }
  }
}
