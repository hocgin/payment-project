# ===========================
# 使用指定版本进行打包
# ===========================
#!/usr/bin/env bash
mvn -Drevision=0.1.0-SNAPSHOT clean package -DskipTests