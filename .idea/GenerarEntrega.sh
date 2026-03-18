#!/bin/bash
cd "$1"
mkdir "GrupoR"
cp -r ".idea" "GrupoR"
cp "inmobilaria.iml" "GrupoR"
cp .gitignore "GrupoR"
cp "README.md" "GrupoR"
cp -r "src" "GrupoR"
tar -cf "GrupoR.zip" "GrupoR"
rm -rf "GrupoR"
printf "Archivo 'GrupoR.tar' creado exitosamente.\n"

