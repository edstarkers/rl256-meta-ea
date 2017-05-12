
SUMMARY = "Produces a Manufacturing Tool compatible Linux Kernel"

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-dtb.inc


SRC_URI = "git://github.com/embeddedartists/linux-imx.git;protocol=git;branch=${SRCBRANCH}"

LOCALVERSION = "-2.0.3"
SRCBRANCH = "ea_4.1.15_2.0.0"
SRCREV = "aebc76f951e76d71dbb7cd0d35b6acabdf813497"
DEPENDS += "lzop-native bc-native"

COMPATIBLE_MACHINE = "(mx6|mx6ul|mx7)"

addtask copy_defconfig after do_unpack before do_preconfigure
do_copy_defconfig () {
    # copy latest ea_imx_mfg_defconfig to use
    cp ${S}/arch/arm/configs/ea_imx_mfg_defconfig ${B}/.config
    cp ${S}/arch/arm/configs/ea_imx_mfg_defconfig ${B}/../defconfig
}


require recipes-kernel/linux/linux-mfgtool.inc

do_deploy () {
    install -d ${DEPLOY_DIR_IMAGE}
    install  arch/arm/boot/zImage ${DEPLOY_DIR_IMAGE}/zImage_mfgtool
}
