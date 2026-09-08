// Interações de front-end do Mercado Express
document.addEventListener('DOMContentLoaded', () => {
    // Fechar automaticamente alertas após 5 segundos
    const alerts = document.querySelectorAll('.alert-dismissible');
    alerts.forEach(alert => {
        setTimeout(() => {
            const bsAlert = bootstrap.Alert.getOrCreateInstance(alert);
            if (bsAlert) {
                bsAlert.close();
            }
        }, 5000);
    });
});

// Função para confirmar exclusão com modal dinâmico
function confirmarExclusao(id, nome) {
    const modalElement = document.getElementById('modalExcluir');
    const nomeElement = document.getElementById('nomeProdutoExcluir');
    const linkElement = document.getElementById('linkConfirmarExcluir');
    
    if (modalElement && nomeElement && linkElement) {
        nomeElement.textContent = nome;
        linkElement.href = '/produtos/excluir/' + id;
        const modal = new bootstrap.Modal(modalElement);
        modal.show();
    }
}
