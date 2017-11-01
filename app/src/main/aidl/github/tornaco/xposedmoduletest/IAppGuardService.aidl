// IAppGuardService.aidl
package github.tornaco.xposedmoduletest;

import github.tornaco.xposedmoduletest.IWatcher;
import github.tornaco.xposedmoduletest.x.bean.BlurSettings;
import github.tornaco.xposedmoduletest.x.bean.PackageSettings;
import github.tornaco.xposedmoduletest.x.bean.VerifySettings;

interface IAppGuardService {
    boolean isEnabled();
    void setEnabled(boolean enabled);

    boolean isUninstallInterruptEnabled();
    void setUninstallInterruptEnabled(boolean enabled);

    void setVerifySettings(in VerifySettings settings);

    VerifySettings getVerifySettings();

    void setBlurSettings(in BlurSettings settings);
    BlurSettings getBlurSettings();

    void setResult(int transactionID, int res);

    void testUI();

    void addPackages(in PackageSettings pkg);
    void removePackages(in PackageSettings pkg);

    List<PackageSettings> getPackageSettings();

    void watch(in IWatcher w);
    void unWatch(in IWatcher w);

    // For test only.
    void mockCrash();
}
