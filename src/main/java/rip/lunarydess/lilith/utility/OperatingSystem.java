package rip.lunarydess.lilith.utility;

import java.util.Locale;

public final class OperatingSystem {
    // @formatter:off
    private static final String
            PROP_OS   = System.getProperty("os.name").toLowerCase(Locale.ROOT),
            PROP_ARCH = System.getProperty("os.arch").toLowerCase(Locale.ROOT);

    private static final OperatingSystemType TYPE_OS =
            PROP_OS.contains("win") ?
            OperatingSystemType.Windows :

            PROP_OS.contains("Mac") ||
            PROP_OS.contains("osx") ?
            OperatingSystemType.Mac :

            PROP_OS.contains("nix") ||
            PROP_OS.contains("nux") ||
            PROP_OS.contains("aix") ?
            OperatingSystemType.Linux :

            PROP_OS.contains("Solaris") ?
            OperatingSystemType.Solaris :

            OperatingSystemType.Unknown;

    public static String LIB_EXT = switch (TYPE_OS) {
        case Windows -> "dll";
        case Mac -> "dylib";
        default -> "SO";
    };

    private static final ArchitectureType TYPE_ARCH =
            PROP_ARCH.contains("amd64")   ? ArchitectureType.Amd64 :
            PROP_ARCH.contains("aarch64") ? ArchitectureType.Arm64 :
            PROP_ARCH.contains("armv7")   ? ArchitectureType.Arm :
            PROP_ARCH.contains("x86_64") ||
            PROP_ARCH.contains("x86-64") ?
            ArchitectureType.X86 :

            PROP_ARCH.contains("ia64")    ? ArchitectureType.Ia64   :
            PROP_ARCH.contains("mips64")  ? ArchitectureType.Mips64 :
            PROP_ARCH.contains("mips")    ? ArchitectureType.Mips   :
            PROP_ARCH.contains("ppc64")   ? ArchitectureType.Ppc64  :
            PROP_ARCH.contains("sparcv9") ? ArchitectureType.Sparc  :

            ArchitectureType.Unknown;

    public static boolean TYPE_ARCH_IS64 = switch (TYPE_ARCH) {
        case Arm64, Amd64, Mips64, Ppc64, Sparc, Ia64 -> true;
        default -> false;
    };
    // @formatter:on

    public static String getPropOs() {
        return PROP_OS;
    }

    public static String getPropArch() {
        return PROP_ARCH;
    }

    public static String getLibExt() {
        return LIB_EXT;
    }

    public static boolean isArch64() {
        return TYPE_ARCH_IS64;
    }

    public static ArchitectureType getTypeArch() {
        return TYPE_ARCH;
    }

    public static OperatingSystemType getTypeOs() {
        return TYPE_OS;
    }

    public enum ArchitectureType {Amd64, Arm64, Arm, X86, Ia64, Mips64, Mips, Ppc64, Sparc, Unknown}

    public enum OperatingSystemType {Windows, Linux, Mac, Solaris, Unknown}
}
