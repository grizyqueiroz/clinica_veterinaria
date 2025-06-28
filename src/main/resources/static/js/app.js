// Custom JavaScript for Clinica Veterinaria Web

$(document).ready(function() {
    
    // Auto-hide alerts after 5 seconds
    setTimeout(function() {
        $('.alert').fadeOut('slow');
    }, 5000);
    
    // Form validation enhancement
    $('form').on('submit', function() {
        var submitBtn = $(this).find('button[type="submit"]');
        var originalText = submitBtn.text();
        
        // Show loading state
        submitBtn.prop('disabled', true);
        submitBtn.html('<span class="loading"></span> Processando...');
        
        // Re-enable after 3 seconds (fallback)
        setTimeout(function() {
            submitBtn.prop('disabled', false);
            submitBtn.text(originalText);
        }, 3000);
    });
    
    // Price calculation for payments
    $('#calcularPreco').on('click', function() {
        var nomePet = $('#nomePet').val();
        var servico = $('#servico').val();
        var estrategia = $('#estrategia').val();
        
        if (nomePet && servico) {
            $.get('/pagamentos/calcular-preco', {
                nomePet: nomePet,
                servico: servico,
                estrategia: estrategia
            })
            .done(function(response) {
                $('#precoCalculado').text(response);
                $('#precoCalculado').removeClass('d-none');
            })
            .fail(function() {
                $('#precoCalculado').text('Erro ao calcular preço');
                $('#precoCalculado').removeClass('d-none');
            });
        }
    });
    
    // Date picker enhancement
    $('input[type="date"]').on('change', function() {
        var selectedDate = new Date($(this).val());
        var today = new Date();
        
        if (selectedDate < today) {
            alert('A data selecionada não pode ser no passado!');
            $(this).val('');
        }
    });
    
    // Table row hover effects
    $('.table tbody tr').hover(
        function() {
            $(this).addClass('table-hover');
        },
        function() {
            $(this).removeClass('table-hover');
        }
    );
    
    // Modal enhancements
    $('.modal').on('show.bs.modal', function() {
        $(this).find('.modal-body').scrollTop(0);
    });
    
    // Tooltip initialization
    $('[data-bs-toggle="tooltip"]').tooltip();
    
    // Confirmation dialogs
    $('.btn-delete').on('click', function(e) {
        if (!confirm('Tem certeza que deseja excluir este item?')) {
            e.preventDefault();
        }
    });
    
    // Search functionality
    $('#searchInput').on('keyup', function() {
        var value = $(this).val().toLowerCase();
        $('.table tbody tr').filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
    });
    
    // Status badge colors
    $('.badge').each(function() {
        var status = $(this).text().toLowerCase();
        if (status.includes('aprovado')) {
            $(this).addClass('bg-success');
        } else if (status.includes('pendente')) {
            $(this).addClass('bg-warning');
        } else if (status.includes('cancelado')) {
            $(this).addClass('bg-danger');
        }
    });
    
    // Smooth scrolling for anchor links
    $('a[href^="#"]').on('click', function(e) {
        e.preventDefault();
        var target = $(this.getAttribute('href'));
        if (target.length) {
            $('html, body').stop().animate({
                scrollTop: target.offset().top - 70
            }, 1000);
        }
    });
    
    // Form field validation feedback
    $('.form-control').on('blur', function() {
        if ($(this).val().trim() === '') {
            $(this).addClass('is-invalid');
        } else {
            $(this).removeClass('is-invalid').addClass('is-valid');
        }
    });
    
    // Auto-format phone numbers
    $('#telefone').on('input', function() {
        var value = $(this).val().replace(/\D/g, '');
        if (value.length === 11) {
            value = value.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
        }
        $(this).val(value);
    });
    
    // Print functionality
    $('.btn-print').on('click', function() {
        window.print();
    });
    
    // Export to CSV (basic implementation)
    $('.btn-export').on('click', function() {
        var table = $('.table');
        var csv = [];
        var rows = table.find('tr');
        
        for (var i = 0; i < rows.length; i++) {
            var row = [], cols = rows[i].querySelectorAll('td, th');
            for (var j = 0; j < cols.length; j++) {
                row.push('"' + cols[j].innerText + '"');
            }
            csv.push(row.join(','));
        }
        
        var csvContent = 'data:text/csv;charset=utf-8,' + csv.join('\n');
        var encodedUri = encodeURI(csvContent);
        var link = document.createElement('a');
        link.setAttribute('href', encodedUri);
        link.setAttribute('download', 'relatorio.csv');
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    });
    
    // Dashboard charts (placeholder for future implementation)
    function initCharts() {
        // TODO: Implement charts using Chart.js or similar library
        console.log('Charts initialization placeholder');
    }
    
    // Initialize charts if on dashboard page
    if (window.location.pathname === '/dashboard') {
        initCharts();
    }
    
    // Responsive table wrapper
    $('.table-responsive').each(function() {
        if ($(this).find('.table').width() > $(this).width()) {
            $(this).addClass('table-scroll');
        }
    });
    
    // Keyboard shortcuts
    $(document).keydown(function(e) {
        // Ctrl/Cmd + N for new item
        if ((e.ctrlKey || e.metaKey) && e.keyCode === 78) {
            e.preventDefault();
            $('.btn-new').first().click();
        }
        
        // Ctrl/Cmd + S for save
        if ((e.ctrlKey || e.metaKey) && e.keyCode === 83) {
            e.preventDefault();
            $('form').submit();
        }
        
        // Escape to close modals
        if (e.keyCode === 27) {
            $('.modal').modal('hide');
        }
    });
    
    // Performance monitoring
    console.log('Clinica Veterinaria Web - Application loaded successfully');
    console.log('Version: 1.0.0');
    console.log('Architecture: MVC + 9 GoF Patterns');
    console.log('Framework: Spring Boot + Thymeleaf');
}); 