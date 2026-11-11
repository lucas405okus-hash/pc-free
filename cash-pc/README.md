# CASH PC — Android (base Winlator)

## Direção do projeto
CASH PC passa a usar o projeto aberto Winlator como base de compatibilidade Windows no Android, em vez de tentar implementar QEMU/uma máquina virtual Windows completa.

O Winlator executa aplicativos Windows x86/x64 por meio de Wine e Box86/Box64. **Ele não inicializa uma instalação completa do Windows por ISO.**

## Integração em andamento
- Workflow `.github/workflows/cash-pc-winlator.yml` clona o repositório oficial Winlator com seus submódulos.
- Compila o app Android upstream usando JDK 17, Android NDK 24 e CMake 3.22.1.
- Ajusta o nome exibido do aplicativo para CASH PC.
- Publica o APK debug como artefato `CASH-PC-Winlator-Android-debug`.

O APK só deve ser considerado pronto depois que o GitHub Actions terminar com sucesso e o artefato for testado em um aparelho real.

## Chrome
O próximo passo de produto é oferecer instalação guiada do Google Chrome dentro de um container Winlator. O instalador deve ser obtido de fonte oficial do Google; não será embutida uma cópia modificada ou não autorizada do navegador. A compilação inicial desta integração **ainda não pré-instala o Chrome**.

## Compatibilidade
- Projetado para Android ARM64.
- Aplicativos Windows x86/x64 dependem de tradução/emulação e podem ter desempenho ou compatibilidade variáveis.
- Não é Windows completo; não promete executar todos os programas.
- O Chrome para Windows pode funcionar no Wine/Box64, mas isso precisa ser validado no aparelho alvo.

## Licenças e atribuições
O Winlator e seus componentes têm licenças próprias. Esta integração mantém os avisos e créditos upstream e não remove as licenças dos componentes. Consulte os repositórios oficiais:
- https://github.com/brunodev85/winlator
- https://github.com/brunodev85/winlator-app

O CASH PC não inclui mídia do Microsoft Windows nem redistribui instaladores proprietários do Google.
