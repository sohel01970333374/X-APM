package github.tornaco.xposedmoduletest.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import github.tornaco.xposedmoduletest.bean.BootCompletePackage;
import github.tornaco.xposedmoduletest.util.PinyinComparator;
import github.tornaco.xposedmoduletest.xposed.app.XAshmanManager;
import github.tornaco.xposedmoduletest.xposed.util.PkgUtil;

/**
 * Created by guohao4 on 2017/10/18.
 * Email: Tornaco@163.com
 */

public interface BootPackageLoader {

    @NonNull
    List<BootCompletePackage> loadInstalled(boolean blocked);

    class Impl implements BootPackageLoader {

        public static BootPackageLoader create(Context context) {
            return new Impl(context);
        }

        private Context context;

        private Impl(Context context) {
            this.context = context;
        }

        @NonNull
        @Override
        public List<BootCompletePackage> loadInstalled(boolean blocked) {
            List<BootCompletePackage> out = new ArrayList<>();

            XAshmanManager xAshmanManager = XAshmanManager.get();
            if (!xAshmanManager.isServiceAvailable()) return out;

            String[] packages = xAshmanManager.getBootBlockApps(blocked);

            for (String pkg : packages) {
                if (!PkgUtil.isPkgInstalled(context, pkg)) continue;
                String name = String.valueOf(PkgUtil.loadNameByPkgName(context, pkg));

                if (!TextUtils.isEmpty(name)) {
                    name = name.replace(" ", "");
                }

                BootCompletePackage p = new BootCompletePackage();
                p.setAllow(false);
                p.setAppName(name);
                p.setPkgName(pkg);
                p.setSystemApp(PkgUtil.isSystemApp(context, pkg));
                out.add(p);
            }

            java.util.Collections.sort(out, new BootComparator());

            return out;
        }
    }

    class BootComparator implements Comparator<BootCompletePackage> {
        public int compare(BootCompletePackage o1, BootCompletePackage o2) {
            return new PinyinComparator().compare(o1.getAppName(), o2.getAppName());
        }
    }
}
