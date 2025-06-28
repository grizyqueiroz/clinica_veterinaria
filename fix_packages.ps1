# Script para corrigir declarações de package em arquivos Java
Write-Host "Iniciando correção automática de packages..."

# Função para obter o package correto baseado no caminho do arquivo
function Get-CorrectPackage {
    param([string]$FilePath)
    
    if ($FilePath -match "src\\main\\java\\(.+)\.java$") {
        $relativePath = $matches[1]
        # Remover o nome da classe do final do caminho
        $directoryPath = Split-Path $relativePath -Parent
        $package = $directoryPath.Replace("\", ".")
        return "package $package;"
    }
    return $null
}

# Encontrar todos os arquivos Java
$javaFiles = Get-ChildItem -Path "src" -Filter "*.java" -Recurse

$fixedCount = 0

foreach ($file in $javaFiles) {
    $content = Get-Content -Path $file.FullName -Raw -Encoding UTF8
    $correctPackage = Get-CorrectPackage $file.FullName
    
    if ($correctPackage) {
        # Verificar se o package atual está incorreto
        if ($content -match "^package\s+[^;]+;") {
            $currentPackage = $matches[0]
            if ($currentPackage -ne $correctPackage) {
                # Substituir o package incorreto
                $newContent = $content -replace "^package\s+[^;]+;", $correctPackage
                Set-Content -Path $file.FullName -Value $newContent -Encoding UTF8
                Write-Host "Corrigido: $($file.FullName)"
                Write-Host "  De: $currentPackage"
                Write-Host "  Para: $correctPackage"
                $fixedCount++
            }
        } else {
            # Adicionar package se não existir
            $newContent = "$correctPackage`n`n$content"
            Set-Content -Path $file.FullName -Value $newContent -Encoding UTF8
            Write-Host "Adicionado package: $($file.FullName)"
            Write-Host "  Package: $correctPackage"
            $fixedCount++
        }
    }
}

Write-Host "`nCorreção concluída! $fixedCount arquivos foram corrigidos." 